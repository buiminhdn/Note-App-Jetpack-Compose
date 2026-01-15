package com.example.noteapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.repositories.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        loadNotes()
    }

    fun dispatch(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.DeleteNote -> deleteNote(intent.id)
            is HomeIntent.ClickNote -> {
                // emit navigation event
            }
        }
    }

    private fun loadNotes() = viewModelScope.launch {
        repo.getNotes()
            .onStart { _state.update { it.copy(isLoading = true, error = null) } }
            .catch { e ->
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
            .collect { notes ->
                _state.update { it.copy(isLoading = false, notes = notes) }
            }
    }

    private fun deleteNote(id: Int) = viewModelScope.launch {
        repo.deleteNote(id)
    }
}