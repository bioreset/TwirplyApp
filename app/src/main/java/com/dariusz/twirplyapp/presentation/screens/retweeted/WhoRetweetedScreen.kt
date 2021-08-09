package com.dariusz.twirplyapp.presentation.screens.retweeted

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.tweet.WhoRetweetedList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun WhoRetweetedScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = WhoRetweetedScreenViewModel(provideUserContextRepository()),
        inputFromVM = { viewModel ->
            viewModel.whoRetweeted
        },
        launchEffect = { viewModel ->
            viewModel.fetchRetweets(tweetID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
                response.outputOne?.let { list ->
                    WhoRetweetedList(list) { userID ->
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