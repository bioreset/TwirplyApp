package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideTweetRepository
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.tweet.ShowTweet
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.InitScreen
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel


@ExperimentalCoilApi
@Composable
fun TweetScreenTest(
    tweetID: String,
    navController: NavController,
    token: String
) = InitScreen(
    navController = navController,
    viewModel = TweetScreenViewModel(provideTweetRepository()),
    inputFromVM = {
        it.fullTweetContent
    },
    launchEffect = {
        it.getAllTweetData(tweetID, token)
    },
    composable = { data, nav ->
        ManageTweetScreen(data, nav)
    }
)

@ExperimentalCoilApi
@Composable
fun TweetScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {

    val tweetScreenViewModel = composeViewModel {
        TweetScreenViewModel(
            provideTweetRepository()
        )
    }

    val fullTweetData by remember(tweetScreenViewModel) {
        tweetScreenViewModel.fullTweetContent
    }.collectAsState()

    LaunchedEffect(Unit) {
        tweetScreenViewModel.getAllTweetData(tweetID, token)
    }

    ManageTweetScreen(fullTweetData, navController)
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
                    navController
                )
            }
        }
    }
}
