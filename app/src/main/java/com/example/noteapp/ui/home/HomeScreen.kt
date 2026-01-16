package com.example.noteapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.navigation.NavScreen
import com.example.noteapp.ui.home.components.HomeAddButton
import com.example.noteapp.ui.home.components.HomeEmptyView
import com.example.noteapp.ui.home.components.HomeListNote
import com.example.noteapp.ui.home.components.HomeTopBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationEvent.collect { route ->
            navController.navigate(route)
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClick = { /* TODO: Làm sau hehe */ },
                onAuthorClick = {
                    uriHandler.openUri("https://www.facebook.com/bmint1709")
                }
            )
        },
        floatingActionButton = {
            HomeAddButton {
                navController.navigate(NavScreen.DetailScreen.route)
            }
        }
    ) { paddingValues ->
        AnimatedVisibility(visible = state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        AnimatedVisibility(visible = !state.isLoading && state.notes.isEmpty()) {
            HomeEmptyView()
        }

        AnimatedVisibility(visible = !state.isLoading && state.notes.isNotEmpty()) {
            HomeListNote(
                notes = state.notes,
                modifier = Modifier.padding(paddingValues),
                onNoteClick = { noteId ->
                    viewModel.dispatch(HomeIntent.ClickNote(noteId))
                },
                onNoteDelete = { noteId ->
                    viewModel.dispatch(HomeIntent.DeleteNote(noteId))
                }
            )
        }
    }
}