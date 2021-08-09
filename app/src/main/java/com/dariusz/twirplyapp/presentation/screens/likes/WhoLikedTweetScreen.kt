package com.dariusz.twirplyapp.presentation.screens.likes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.tweet.WhoLikedList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun WhoLikedTweetScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = WhoLikedTweetScreenViewModel(provideUserContextRepository()),
        inputFromVM = { viewModel ->
            viewModel.whoLikedTweet
        },
        launchEffect = { viewModel ->
            viewModel.getUsersWhoLikedTweet(tweetID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
                response.outputOne?.let { list ->
                    WhoLikedList(list) { userID ->
                        navController.navigateToWithArgument(
                            Screens.AppScreens.ProfileScreen.route,
                            userID
                        )
                    }
                }
            }
        }
    )
}
