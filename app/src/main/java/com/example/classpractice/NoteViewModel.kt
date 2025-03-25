package com.example.classpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = NoteDatabase.getDatabase(application).noteDao()

    val noteList: LiveData<List<Note>> = dao.getAllNotes()

    suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)

    fun insertNote(note: Note) = viewModelScope.launch {
        dao.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        dao.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        dao.delete(note)
    }
}
