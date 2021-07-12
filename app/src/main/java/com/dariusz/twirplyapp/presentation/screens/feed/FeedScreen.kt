package com.dariusz.twirplyapp.presentation.screens.feed

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.twirplyapp.presentation.MainViewModel

@Composable
fun FeedScreen(
    mainViewModel: MainViewModel = viewModel()
) {

    Text("Feed Screen")

}