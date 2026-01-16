package com.example.noteapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.entities.models.Note
import com.example.noteapp.data.repositories.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    fun dispatch(intent: DetailIntent) {
        when (intent) {

            is DetailIntent.LoadNote -> loadNote(intent.id)

            is DetailIntent.ChangeTitle ->
                _state.update { it.copy(title = intent.text) }

            is DetailIntent.ChangeContent ->
                _state.update { it.copy(content = intent.text) }

            is DetailIntent.ChangeBackgroundColor ->
                _state.update { it.copy(backgroundColor = intent.backgroundColor) }

            DetailIntent.Save -> save()

            DetailIntent.ClearErrorMessage -> {
                _state.update { it.copy(errorMessage = null) }
            }

            DetailIntent.ConfirmDelete -> {
                deleteNote()
            }
        }
    }

    private fun loadNote(id: Int) = viewModelScope.launch {
        val note = repo.getNoteById(id)
        if (note == null) {
            _state.update { it.copy(isFinished = true) }
            return@launch
        }

        _state.update {
            it.copy(
                id = note.id,
                title = note.title,
                content = note.content,
                backgroundColor = note.backgroundColor
            )
        }
    }

    private fun deleteNote() = viewModelScope.launch {
        _state.value.id?.let { noteId ->
            repo.deleteNote(noteId)
            _state.update { it.copy(isFinished = true) }
        }
    }

    private fun save() = viewModelScope.launch {
        if (_state.value.title.isBlank() || _state.value.content.isBlank()) {
            _state.update {
                it.copy(errorMessage = "Vui lòng nhập tiêu đề và nội dung")
            }
            return@launch
        }

        val noteToSave = if (_state.value.id != null) {
            Note(
                id = _state.value.id!!,
                title = _state.value.title,
                content = _state.value.content,
                backgroundColor = _state.value.backgroundColor
            )
        } else {
            Note(
                title = _state.value.title,
                content = _state.value.content,
                backgroundColor = _state.value.backgroundColor
            )
        }

        repo.insertNote(noteToSave)
        _state.update { it.copy(isFinished = true) }
    }
}