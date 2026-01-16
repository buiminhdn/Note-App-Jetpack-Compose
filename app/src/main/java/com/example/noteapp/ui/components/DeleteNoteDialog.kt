package com.example.noteapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun DeleteNoteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        shape = RectangleShape,
        onDismissRequest = onDismiss,
        title = { Text("Xóa ghi chú") },
        text = { Text("Bạn có chắc chắn muốn xóa ghi chú này không?") },
        confirmButton = {
            Text(
                text = "Xóa",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = onConfirm),
                color = MaterialTheme.colorScheme.error
            )
        },
        dismissButton = {
            Text(
                text = "Hủy",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = onDismiss)
            )
        }
    )
}
