package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun MainLayout(
    bottomBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            bottomBar.invoke()
        }
    ) {
        content.invoke()
    }
}

