package com.dariusz.twirplyapp.presentation.screens.likes

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.tweet.WhoLikedList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel


@ExperimentalCoilApi
@Composable
fun WhoLikedTweetScreen(
    tweetID: String,
    navController: NavController,
    token: String
) {

    val whoLikedTweetScreenViewModel = composeViewModel {
        WhoLikedTweetScreenViewModel(
            provideUserContextRepository()
        )
    }

    val whoRetweeted by remember(whoLikedTweetScreenViewModel) {
        whoLikedTweetScreenViewModel.whoLikedTweet
    }.collectAsState()

    ManageWhoLikedTweetScreen(whoRetweeted) {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        whoLikedTweetScreenViewModel.getUsersWhoLikedTweet(tweetID, token)
    }
}

@ExperimentalCoilApi
@Composable
fun ManageWhoLikedTweetScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>,
    action: (String) -> Unit
) {
    ManageResponseOnScreen(input = input) { response ->
        response.outputOne?.let { list ->
            WhoLikedList(input = list) { userID ->
                action.invoke(userID)
            }
        }
    }
}