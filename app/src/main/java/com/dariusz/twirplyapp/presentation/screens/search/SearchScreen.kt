package com.dariusz.twirplyapp.presentation.screens.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.common.SearchBox

@Composable
fun SearchScreen(navController: NavController){
    SearchBox(navController)
}