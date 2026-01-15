package com.example.noteapp.data.repositories.note

import com.example.noteapp.data.entities.models.Note
import com.example.noteapp.data.local.dao.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? = withContext(Dispatchers.IO) {
        noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(id: Int) = withContext(Dispatchers.IO) {
        noteDao.deleteNoteById(id)
    }

}