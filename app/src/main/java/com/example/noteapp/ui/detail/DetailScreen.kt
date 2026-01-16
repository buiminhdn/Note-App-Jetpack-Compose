package com.example.noteapp.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.R
import com.example.noteapp.ui.components.CustomIconButton
import com.example.noteapp.ui.components.DeleteNoteDialog
import com.example.noteapp.ui.detail.components.ColorPickerRow
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.ui.theme.Nunito
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    noteId: Int?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val isNewNote = noteId == null

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (noteId != null) {
            viewModel.dispatch(DetailIntent.LoadNote(noteId))
        }
    }

    LaunchedEffect(key1 = state.isFinished) {
        if (state.isFinished) {
            navController.navigateUp()
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            scope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.dispatch(DetailIntent.ClearErrorMessage)
            }
        }
    }

    if (showDeleteDialog) {
        DeleteNoteDialog(
            onConfirm = {
                viewModel.dispatch(DetailIntent.ConfirmDelete)
                showDeleteDialog = false
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(16.dp),
                title = { /* Không có title */ },
                navigationIcon = {
                    CustomIconButton(
                        onClick = { navController.navigateUp() },
                        iconResId = R.drawable.ic_back,
                        contentDescription = ""
                    )
                },
                actions = {
                    Row {
                        if (!isNewNote) {
                            CustomIconButton(
                                onClick = { showDeleteDialog = true },
                                iconResId = R.drawable.ic_delete,
                                contentDescription = stringResource(R.string.delete_note)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }

                        CustomIconButton(
                            onClick = { viewModel.dispatch(DetailIntent.Save) },
                            iconResId = R.drawable.ic_save,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ColorPickerRow(
                selectedColor = state.backgroundColor,
                onColorSelected = { color ->
                    viewModel.dispatch(DetailIntent.ChangeBackgroundColor(color))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                TextField(
                    value = state.title,
                    onValueChange = { newTitle ->
                        viewModel.dispatch(DetailIntent.ChangeTitle(newTitle))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.title),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito,
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )

                TextField(
                    value = state.content,
                    onValueChange = { newContent ->
                        viewModel.dispatch(DetailIntent.ChangeContent(newContent))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.type_something),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito,
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "New Note Preview")
@Composable
fun DetailScreenNewNotePreview() {
    NoteAppTheme {
        DetailScreen(
            navController = rememberNavController(),
            noteId = null
        )
    }
}

@Preview(showBackground = true, name = "Edit Note Preview")
@Composable
fun DetailScreenEditNotePreview() {
    NoteAppTheme {
        DetailScreen(
            navController = rememberNavController(),
            noteId = 1
        )
    }
}