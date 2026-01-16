package com.example.noteapp.ui.detail

import com.example.noteapp.data.entities.enums.NoteColor

sealed class DetailIntent {
    data class LoadNote(val id: Int) : DetailIntent()
    data class ChangeTitle(val text: String) : DetailIntent()
    data class ChangeContent(val text: String) : DetailIntent()
    data class ChangeBackgroundColor(val backgroundColor: NoteColor) : DetailIntent()
    object Save : DetailIntent()
    object ClearErrorMessage : DetailIntent()
    object ConfirmDelete : DetailIntent()
}
