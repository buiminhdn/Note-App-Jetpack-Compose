package com.example.noteapp.ui.detail

import com.example.noteapp.data.entities.models.Note

data class DetailState(
    val note: Note? = null,
    val isSaving: Boolean = false,
    val isFinished: Boolean = false
)
