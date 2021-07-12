package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun MainLayout(
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            topBar.invoke()
        },
        bottomBar = {
            bottomBar.invoke()
        }
    ) {
        content.invoke()
    }
}

