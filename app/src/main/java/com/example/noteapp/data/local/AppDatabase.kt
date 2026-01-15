package com.example.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.entities.models.Note
import com.example.noteapp.data.local.dao.NoteDao

@Database(
    entities = [Note::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        const val DB_NAME = "notes_db"
    }
}