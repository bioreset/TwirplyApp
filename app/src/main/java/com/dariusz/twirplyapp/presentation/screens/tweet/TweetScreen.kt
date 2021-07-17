package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.tweets.ShowTweet

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

    when (tweet) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            LazyColumn {
                item {
                    ShowTweet(
                        tweet.data,
                        navController = navController
                    )
                }
            }
        }
        is ResponseState.Error -> {
            Text("Tweet Error")
        }
        else -> {
            Text("Tweet Here")
        }
    }


}
