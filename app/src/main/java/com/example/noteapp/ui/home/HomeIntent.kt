package com.example.noteapp.ui.home

sealed class HomeIntent {
    //    object LoadNotes : HomeIntent()
    data class DeleteNote(val id: Int) : HomeIntent()
    data class ClickNote(val id: Int) : HomeIntent()
}