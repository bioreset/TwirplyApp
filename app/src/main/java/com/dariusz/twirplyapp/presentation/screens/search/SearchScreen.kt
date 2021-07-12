package com.dariusz.twirplyapp.presentation.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.search.SearchBox

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController) {
    SearchBox(navController)
}