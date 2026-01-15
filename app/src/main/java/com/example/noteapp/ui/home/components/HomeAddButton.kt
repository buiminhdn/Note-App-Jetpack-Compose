package com.example.noteapp.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.theme.AppBackgroundColor
import com.example.noteapp.ui.theme.NoteAppTheme

@Composable
fun HomeAddButton(
    onFabClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        FloatingActionButton(
            modifier = Modifier.size(70.dp),
            onClick = onFabClick,
            shape = CircleShape,
            containerColor = AppBackgroundColor,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = stringResource(id = R.string.add_note)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAddButtonPreview() {
    NoteAppTheme {
        HomeAddButton(
            onFabClick = {}
        )
    }
}