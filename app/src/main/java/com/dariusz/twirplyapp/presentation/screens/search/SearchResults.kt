package com.dariusz.twirplyapp.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideSearchRepository
import com.dariusz.twirplyapp.presentation.components.search.SearchResultsList
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun SearchResults(
    query: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = SearchScreenViewModel(provideSearchRepository()),
        inputFromVM = { viewModel ->
            viewModel.finalSearchResults
        },
        launchEffect = { viewModel ->
            viewModel.fetchSearchResultsForQuery(query, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) {
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
    )
}
