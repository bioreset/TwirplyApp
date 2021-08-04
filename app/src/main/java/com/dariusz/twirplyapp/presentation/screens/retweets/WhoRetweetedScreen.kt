package com.dariusz.twirplyapp.presentation.screens.retweets

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.tweet.WhoRetweetedList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel

@ExperimentalCoilApi
@Composable
fun WhoRetweetedScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {

    val whoRetweetedScreenViewModel = composeViewModel {
        WhoRetweetedScreenViewModel(
            provideUserContextRepository()
        )
    }

    val whoRetweeted by remember(whoRetweetedScreenViewModel) {
        whoRetweetedScreenViewModel.whoRetweeted
    }.collectAsState()

    ManageWhoRetweetedScreen(whoRetweeted) {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        whoRetweetedScreenViewModel.fetchRetweets(tweetID, token)
    }
}

@ExperimentalCoilApi
@Composable
fun ManageWhoRetweetedScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>,
    action: (String) -> Unit
) {
    ManageResponseOnScreen(input = input) { response ->
        response.outputOne?.let { list ->
            WhoRetweetedList(input = list) { userID ->
                action.invoke(userID)
            }
        }
    }
}