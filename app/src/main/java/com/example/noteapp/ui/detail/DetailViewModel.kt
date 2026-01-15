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
                updateNote { copy(title = intent.text) }

            is DetailIntent.ChangeContent ->
                updateNote { copy(content = intent.text) }

            is DetailIntent.ChangeBackgroundColor ->
                updateNote { copy(backgroundColor = intent.backgroundColor) }

            DetailIntent.Save -> save()
        }
    }

    private fun loadNote(id: Int) = viewModelScope.launch {
        if (id != 0) {
            _state.update {
                it.copy(note = repo.getNoteById(id))
            }
        }
    }

    private fun updateNote(block: Note.() -> Note) {
        _state.update { currentState ->
            val updatedNote = currentState.note?.block()
            currentState.copy(note = updatedNote)
        }
    }

    private fun save() = viewModelScope.launch {
        val note = _state.value.note ?: return@launch

        _state.update { it.copy(isSaving = true) }
        repo.insertNote(note)
        _state.update { it.copy(isSaving = false, isFinished = true) }
    }
}