package com.example.classpractice

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.classpractice.ui.theme.ClassPracticeTheme
import kotlinx.coroutines.launch
import com.example.classpractice.NoteViewModel
import com.example.classpractice.NoteViewModelFactory
import com.example.classpractice.screens.NoteEditScreen
import com.example.classpractice.screens.NoteListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClassPracticeTheme {
                val navController = rememberNavController()
                val viewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(application))

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        NoteListScreen(navController, viewModel)
                    }
                    composable(
                        "edit/{noteId}",
                        arguments = listOf(navArgument("noteId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
                        NoteEditScreen(noteId, viewModel, navController)
                    }
                }
            }
        }
    }
}
