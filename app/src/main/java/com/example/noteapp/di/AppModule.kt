package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.local.AppDatabase
import com.example.noteapp.data.local.dao.NoteDao
import com.example.noteapp.data.repositories.note.NoteRepository
import com.example.noteapp.data.repositories.note.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository {
        return noteRepositoryImpl
    }
}