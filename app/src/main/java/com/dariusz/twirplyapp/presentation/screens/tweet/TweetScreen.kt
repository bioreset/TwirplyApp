package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.twirplyapp.domain.model.ResponseState
import com.dariusz.twirplyapp.domain.model.TweetWithAuthor
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweet

@Composable
fun TweetScreen(
    tweetID: Long,
    mainViewModel: MainViewModel = viewModel()
) {

    val tweetIDToDisplay = remember { mutableStateOf(tweetID) }

    val fullTweetData by remember(mainViewModel) {
        mainViewModel.fullTweetContent
    }.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getAllTweetData(tweetIDToDisplay.value.toInt())
    }

    ManageTweetScreen(tweet = fullTweetData)
}

@Composable
fun ManageTweetScreen(
    tweet: ResponseState<TweetWithAuthor>
) {

    when (tweet) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            DisplayTweet(tweet.data.responseWithTweet, tweet.data.responseWithAuthor)
        }
        is ResponseState.Error -> {
            Text("Tweet Error")
        }
        else -> {
            Text("Tweet Here")
        }
    }


}
