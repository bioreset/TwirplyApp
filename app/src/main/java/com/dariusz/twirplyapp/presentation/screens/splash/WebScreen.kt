package com.dariusz.twirplyapp.presentation.screens.splash


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.common.OpenLink

@Composable
fun WebScreen(
    url: String,
    navController: NavController
) {
    OpenLink(url)
}