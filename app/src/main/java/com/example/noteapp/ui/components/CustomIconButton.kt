package com.example.noteapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.ui.theme.IconButtonBackground

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    @DrawableRes iconResId: Int,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape(15.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = IconButtonBackground
        )
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}