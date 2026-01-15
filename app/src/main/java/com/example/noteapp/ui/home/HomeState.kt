package com.example.noteapp.ui.home

import com.example.noteapp.data.entities.models.Note

data class HomeState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val error: String? = null
)