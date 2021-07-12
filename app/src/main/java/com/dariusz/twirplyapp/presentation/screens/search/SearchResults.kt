package com.dariusz.twirplyapp.presentation.screens.search

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.search.SearchResultsList

@Composable
fun SearchResults(
    query: String,
    searchScreenViewModel: SearchScreenViewModel = viewModel(),
    navController: NavController
) {

    val givenQuery = remember { mutableStateOf(query) }
    val searchResultsList by remember(searchScreenViewModel) {
        searchScreenViewModel.finalSearchResults
    }.collectAsState()

    Text("Search query: $givenQuery")

    ManageSearchResults(searchResultsList, navController)

    LaunchedEffect(Unit) {
        searchScreenViewModel.fetchSearchResultsForQuery(givenQuery.value)
    }

}

@Composable
fun ManageSearchResults(
    searchResults: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
    navController: NavController
) {
    when (searchResults) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            SearchResultsList(searchResults.data, navController)
        }
        is ResponseState.Error -> {
            Text("No tweets")
        }
        else -> {
            Text("another error search")
        }
    }
}