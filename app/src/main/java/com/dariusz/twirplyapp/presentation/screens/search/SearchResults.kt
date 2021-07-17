package com.dariusz.twirplyapp.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.search.SearchResultsList
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography

@ExperimentalCoilApi
@Composable
fun SearchResults(
    query: String,
    searchScreenViewModel: SearchScreenViewModel = viewModel(),
    navController: NavController,
    token: String
) {

    val searchResultsList by remember(searchScreenViewModel) {
        searchScreenViewModel.finalSearchResults
    }.collectAsState()

    ManageSearchResults(searchResultsList, navController, query)

    LaunchedEffect(Unit) {
        searchScreenViewModel.fetchSearchResultsForQuery(query, token)
    }

}

@ExperimentalCoilApi
@Composable
fun ManageSearchResults(
    searchResults: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
    navController: NavController,
    query: String
) {
    when (searchResults) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            Text(
                "Search query: $query",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.Black)
                    .fillMaxWidth(),
                style = ThemeTypography.h1,
            )
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