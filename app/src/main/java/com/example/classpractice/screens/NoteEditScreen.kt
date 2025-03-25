package com.example.classpractice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.classpractice.Note
import com.example.classpractice.NoteViewModel



@Composable
fun NoteEditScreen(
    noteId: Int,
    viewModel: NoteViewModel,
    navController: NavHostController
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // Load the existing note if editing
    LaunchedEffect(noteId) {
        if (noteId != -1) {
            val note = viewModel.getNoteById(noteId)
            if (note != null) {
                title = note.title
                content = note.content
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (noteId == -1) {
                viewModel.insertNote(Note(title = title, content = content))
            } else {
                viewModel.updateNote(Note(id = noteId, title = title, content = content))
            }
            navController.popBackStack()
        }) {
            Text("Save Note")
        }
    }
}
