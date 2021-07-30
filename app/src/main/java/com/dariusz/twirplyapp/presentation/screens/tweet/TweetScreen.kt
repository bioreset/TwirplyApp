package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.tweets.ShowTweet
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen

@ExperimentalCoilApi
@Composable
fun TweetScreen(
    tweetID: String,
    tweetScreenViewModel: TweetScreenViewModel = viewModel(),
    navController: NavController,
    token: String
) {

    val fullTweetData by remember(tweetScreenViewModel) {
        tweetScreenViewModel.fullTweetContent
    }.collectAsState()

    LaunchedEffect(Unit) {
        tweetScreenViewModel.getAllTweetData(tweetID, token)
    }

    ManageTweetScreen(tweet = fullTweetData, navController)
}

@ExperimentalCoilApi
@Composable
fun ManageTweetScreen(
    tweet: ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Meta?>>,
    navController: NavController
) {

    ManageResponseOnScreen(input = tweet) { response ->
        LazyColumn {
            item {
                ShowTweet(
                    response,
                    navController = navController
                )
            }
        }
    }
}
