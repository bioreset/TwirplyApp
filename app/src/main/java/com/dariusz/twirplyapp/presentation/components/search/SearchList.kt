package com.dariusz.twirplyapp.presentation.components.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.TweetWithAuthorList

@ExperimentalCoilApi
@Composable
fun SearchResultsList(
    response: GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>,
    navController: NavController
) = TweetWithAuthorList(response, navController)
