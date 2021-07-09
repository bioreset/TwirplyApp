package com.dariusz.twirplyapp.presentation.screens.search

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.twirplyapp.domain.model.ResponseState
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent

@Composable
fun SearchResults(
    query: String,
    searchScreenViewModel: SearchScreenViewModel = viewModel()
){

    val givenQuery = remember { mutableStateOf(query) }
    val searchResultsList by remember(searchScreenViewModel) {
        searchScreenViewModel.finalSearchResults
    }.collectAsState()

    when (searchResultsList) {
        is ResponseState.Idle -> {} //do nothing
        is ResponseState.Loading -> { LoadingComponent() }
        is ResponseState.Success -> {  } //display results
        is ResponseState.Error -> { Text("No tweets") }
    }

    LaunchedEffect(Unit) {
        searchScreenViewModel.fetchSearchResultsForQuery(givenQuery.value)
    }

}