package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideTweetRepository
import com.dariusz.twirplyapp.presentation.components.tweet.ShowTweet
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun TweetScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = TweetScreenViewModel(provideTweetRepository()),
        inputFromVM = { viewModel ->
            viewModel.fullTweetContent
        },
        launchEffect = { viewModel ->
            viewModel.getAllTweetData(tweetID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
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
    )
}
