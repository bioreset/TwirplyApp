package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun MainLayout(
    bottomBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            bottomBar.invoke()
        },
        content = {
            content.invoke()
        }
    )
}

