package com.example.noteapp.ui.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.components.CustomIconButton
import com.example.noteapp.ui.theme.NoteAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit,
    onAuthorClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.padding(0.dp, 8.dp),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        actions = {
            CustomIconButton(
                onClick = onSearchClick,
                iconResId = R.drawable.ic_search,
                contentDescription = stringResource(id = R.string.search_notes)
            )
            Spacer(modifier = Modifier.size(12.dp))
            CustomIconButton(
                onClick = onAuthorClick,
                iconResId = R.drawable.ic_info,
                contentDescription = stringResource(id = R.string.author_info)
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeTopBarPreview() {
    NoteAppTheme {
        HomeTopBar(
            onSearchClick = {},
            onAuthorClick = {}
        )
    }
}
