package com.example.noteapp.data.entities.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.data.entities.enums.NoteColor

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val updatedAt: Long = System.currentTimeMillis(),
    val backgroundColor: NoteColor
)
