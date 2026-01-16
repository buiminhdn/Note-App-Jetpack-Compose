package com.example.noteapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.data.entities.enums.NoteColor
import com.example.noteapp.data.entities.enums.toColor
import com.example.noteapp.data.entities.models.Note
import com.example.noteapp.ui.components.DeleteNoteDialog
import com.example.noteapp.ui.theme.Black
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.ui.theme.Nunito

@Composable
fun HomeListNote(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onNoteClick: (Int) -> Unit,
    onNoteDelete: (Int) -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedNoteId by remember { mutableStateOf<Int?>(null) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(notes, key = { it.id }) { note ->
            NoteItem(
                note = note,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNoteClick(note.id) },
                onLongPress = {
                    selectedNoteId = note.id
                    showDeleteDialog = true
                }
            )
        }
    }

    if (showDeleteDialog && selectedNoteId != null) {
        DeleteNoteDialog(
            onConfirm = {
                onNoteDelete(selectedNoteId!!)
                showDeleteDialog = false
                selectedNoteId = null
            },
            onDismiss = {
                showDeleteDialog = false
                selectedNoteId = null
            }
        )
    }
}

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(note.backgroundColor.toColor())
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongPress
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = note.title,
                fontWeight = FontWeight.Medium,
                fontFamily = Nunito,
                fontSize = 20.sp,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.content,
                fontWeight = FontWeight.Light,
                fontFamily = Nunito,
                fontSize = 14.sp,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeListNotePreview() {
    val sampleNotes = listOf(
        Note(
            id = 1,
            title = "Đi chợ",
            content = "Mua trứng, sữa, và bánh mì cho bữa sáng.",
            backgroundColor = NoteColor.DEFAULT
        ),
        Note(
            id = 2,
            title = "Họp team hàng tuần",
            content = "Thảo luận về tiến độ dự án và các vấn đề còn tồn đọng.",
            backgroundColor = NoteColor.YELLOW
        ),
        Note(
            id = 3,
            title = "Đọc sách",
            content = "Đọc hết chương 5 của cuốn 'Atomic Habits'.",
            backgroundColor = NoteColor.GREEN
        ),
        Note(
            id = 4,
            title = "Lên kế hoạch du lịch",
            content = "Tìm địa điểm, đặt vé máy bay và khách sạn cho chuyến đi sắp tới.",
            backgroundColor = NoteColor.BLUE
        )
    )

    NoteAppTheme {
        HomeListNote(
            notes = sampleNotes,
            onNoteClick = { },
            onNoteDelete = { }
        )
    }
}