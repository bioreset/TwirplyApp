package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweet

@Composable
fun TweetScreen(
    tweetID: Long,
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController
) {

    val tweetIDToDisplay = remember { mutableStateOf(tweetID) }

    val fullTweetData by remember(mainViewModel) {
        mainViewModel.fullTweetContent
    }.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getAllTweetData(tweetIDToDisplay.value.toInt())
    }

    ManageTweetScreen(tweet = fullTweetData, navController)
}

@Composable
fun ManageTweetScreen(
    tweet: ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Nothing>>,
    navController: NavController
) {

    when (tweet) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            DisplayTweet(
                tweet.data,
                navController = navController
            )
        }
        is ResponseState.Error -> {
            Text("Tweet Error")
        }
        else -> {
            Text("Tweet Here")
        }
    }


}
