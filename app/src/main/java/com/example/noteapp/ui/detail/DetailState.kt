package com.example.noteapp.ui.detail

import com.example.noteapp.data.entities.enums.NoteColor

data class DetailState(
    val id: Int? = null,
    val title: String = "",
    val content: String = "",
    val backgroundColor: NoteColor = NoteColor.DEFAULT,

    val isSaving: Boolean = false,
    val isFinished: Boolean = false,
    val errorMessage: String? = null,
)
