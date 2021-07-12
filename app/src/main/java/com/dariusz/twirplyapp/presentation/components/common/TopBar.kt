package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("TwirplyApp") },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 8.dp,
    )
}