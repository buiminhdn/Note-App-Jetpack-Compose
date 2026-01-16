package com.example.noteapp.data.entities.enums

import androidx.compose.ui.graphics.Color
import com.example.noteapp.ui.theme.NoteBlue
import com.example.noteapp.ui.theme.NoteGreen
import com.example.noteapp.ui.theme.NotePink
import com.example.noteapp.ui.theme.NotePurple
import com.example.noteapp.ui.theme.NoteRed
import com.example.noteapp.ui.theme.NoteWhite
import com.example.noteapp.ui.theme.NoteYellow

enum class NoteColor {
    DEFAULT, PINK, RED, GREEN, YELLOW, BLUE, PURPLE
}

fun NoteColor.toColor(): Color {
    return when (this) {
        NoteColor.PINK -> NotePink
        NoteColor.RED -> NoteRed
        NoteColor.GREEN -> NoteGreen
        NoteColor.YELLOW -> NoteYellow
        NoteColor.BLUE -> NoteBlue
        NoteColor.PURPLE -> NotePurple
        NoteColor.DEFAULT -> NoteWhite
    }
}