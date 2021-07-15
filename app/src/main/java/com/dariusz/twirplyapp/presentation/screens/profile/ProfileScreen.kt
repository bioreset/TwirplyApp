package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.profile.ProfileFeed
import com.dariusz.twirplyapp.presentation.components.profile.UserMentionsTimeline
import com.dariusz.twirplyapp.presentation.components.profile.UserTweetsTimeline

@Composable
fun ProfileScreen(
    profileID: String,
    profileScreenViewModel: ProfileScreenViewModel = viewModel(),
    navController: NavController,
    token: String
) {

    val profile = remember {
        mutableStateOf(profileID)
    }

    val profileIDToDisplay = remember { mutableStateOf("2244994945") }

    val profileToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFullData
    }.collectAsState()

    val userTweets by remember(profileScreenViewModel) {
        profileScreenViewModel.userTweets
    }.collectAsState()

    val userMentions by remember(profileScreenViewModel) {
        profileScreenViewModel.userMentions
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
        profileIDToDisplay.value.let { profileID ->
            profileScreenViewModel.apply {
                getUserFullData(profileID, token)
                getUserTweets(profileID, token)
                getUserMentions(profileID, token)
            }
        }
    }

}

@Composable
fun ManageProfileScreen(
    profile: ResponseState<GenericResponse<User?, Includes?, Errors?, Meta?>>,
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
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
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
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
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