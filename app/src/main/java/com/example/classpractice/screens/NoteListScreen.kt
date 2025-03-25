package com.example.classpractice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.example.classpractice.Note
import com.example.classpractice.NoteViewModel

@Composable
fun NoteListScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val notes by viewModel.noteList.observeAsState(emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Button(onClick = { navController.navigate("edit/-1") }) {
            Text("Create New Note")
        }

        LazyColumn {
            items(notes) { note ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(note.title, style = MaterialTheme.typography.titleMedium)
                        Text(note.content, style = MaterialTheme.typography.bodyMedium)

                        Row {
                            Button(onClick = { navController.navigate("edit/${note.id}") }) {
                                Text("Edit")
                            }
                            Spacer(Modifier.width(8.dp))
                            Button(onClick = { viewModel.deleteNote(note) }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
