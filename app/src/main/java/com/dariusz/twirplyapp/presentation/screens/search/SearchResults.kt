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
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.search.SearchResultsList
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen

@ExperimentalCoilApi
@Composable
fun SearchResults(
    query: String,
    searchScreenViewModel: SearchScreenViewModel,
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
    ManageResponseOnScreen(input = searchResults) {
        Text(
            "Search query: $query",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Black)
                .fillMaxWidth(),
            style = ThemeTypography.h1,
        )
        SearchResultsList(it, navController)
    }
}