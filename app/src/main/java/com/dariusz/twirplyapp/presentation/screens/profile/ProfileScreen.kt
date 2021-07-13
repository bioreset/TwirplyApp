package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.profile.ProfileFeed
import com.dariusz.twirplyapp.presentation.components.profile.UserMentionsTimeline
import com.dariusz.twirplyapp.presentation.components.profile.UserTweetsTimeline

@Composable
fun ProfileScreen(
    profileID: Long,
    profileScreenViewModel: ProfileScreenViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController
) {

    val profileIDToDisplay = remember { mutableStateOf(profileID) }

    val profileToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFullData
    }.collectAsState()

    val userTweets by remember(mainViewModel) {
        mainViewModel.userTweets
    }.collectAsState()

    val userMentions by remember(mainViewModel) {
        mainViewModel.userMentions
    }.collectAsState()

    ManageProfileScreen(
        profile = profileToDisplay,
        tweets = {
            ManageTweets(input = userTweets, navController = navController)
        },
        mentions = {
            ManageMentions(input = userMentions, navController = navController)
        },
        navController = navController
    ) {
        /* TODO: FOLLOW ACTION */
    }

    LaunchedEffect(Unit) {
        profileIDToDisplay.value.toInt().let { profileID ->
            profileScreenViewModel.apply {
                getUserFullData(profileID)
            }
            mainViewModel.apply {
                getUserTweets(profileID)
                getUserMentions(profileID)
            }
        }
    }

}

@Composable
fun ManageProfileScreen(
    profile: ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>,
    tweets: @Composable () -> Unit,
    mentions: @Composable () -> Unit,
    navController: NavController,
    actionFollowUser: (String) -> Unit
) {
    when (profile) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            ProfileFeed(
                user = profile.data,
                tweets = {
                    tweets.invoke()
                },
                mentions = {
                    mentions.invoke()
                },
                navController = navController
            ) {
                actionFollowUser.invoke(it)
            }
        }
        is ResponseState.Error -> {
            Text("Profile Error")
        }
        else -> {
            Text("Profile Here")
        }
    }
}


@Composable
fun ManageTweets(
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>>,
    navController: NavController
) {
    when (input) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            UserTweetsTimeline(input = input.data, navController = navController)
        }
        is ResponseState.Error -> {
            Text("Profile Tweets Error")
        }
        else -> {
            Text("Profile Tweets Here")
        }
    }
}


@Composable
fun ManageMentions(
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>>,
    navController: NavController
) {
    when (input) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            UserMentionsTimeline(input = input.data, navController = navController)
        }
        is ResponseState.Error -> {
            Text("Profile Mentions Error")
        }
        else -> {
            Text("Profile Mentions Here")
        }
    }
}