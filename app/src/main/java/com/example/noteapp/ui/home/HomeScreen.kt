package com.example.noteapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.navigation.NavScreen
import com.example.noteapp.ui.home.components.HomeAddButton
import com.example.noteapp.ui.home.components.HomeEmptyView
import com.example.noteapp.ui.home.components.HomeTopBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClick = { /* TODO: Implement search logic */ },
                onFilterClick = { /* TODO: Implement filter logic */ }
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
                // TODO: Thêm một Composable loading indicator (ví dụ: CircularProgressIndicator)
            }
        }

        AnimatedVisibility(visible = !state.isLoading && state.notes.isEmpty()) {
            HomeEmptyView()
        }

        AnimatedVisibility(visible = !state.isLoading && state.notes.isNotEmpty()) {
            NoteListView(
                notes = state.notes,
                modifier = Modifier.padding(paddingValues),
                onNoteClick = { noteId ->
                    navController.navigate("${NavScreen.DetailScreen.route}?noteId=$noteId")
                }
            )
        }
    }
}

@Composable
fun NoteListView(
    notes: List<com.example.noteapp.data.entities.models.Note>,
    modifier: Modifier = Modifier,
    onNoteClick: (Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(notes) { note ->
            NoteItem(
                note = note,
                modifier = Modifier.clickable { onNoteClick(note.id) }
            )
        }
    }
}

@Composable
fun NoteItem(
    note: com.example.noteapp.data.entities.models.Note,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(8.dp)) {
        Column {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.content, style = MaterialTheme.typography.bodySmall)
        }
    }
}
