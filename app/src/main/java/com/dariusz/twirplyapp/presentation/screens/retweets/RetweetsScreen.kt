package com.dariusz.twirplyapp.presentation.screens.retweets

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.presentation.components.common.RetweetWithAuthorList
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun RetweetsScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = RetweetsViewModel(provideUserContextRepository()),
        inputFromVM = { viewModel ->
            viewModel.retweetList
        },
        launchEffect = { viewModel ->
            viewModel.getRetweetsOfTweet(tweetID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
                RetweetWithAuthorList(response, navController)
            }
        }
    )
}