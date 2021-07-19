package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.profile.ProfileFeed
import com.dariusz.twirplyapp.presentation.components.profile.UserMentionsTimeline
import com.dariusz.twirplyapp.presentation.components.profile.UserTweetsTimeline
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun ProfileScreen(
    profileID: String,
    profileScreenViewModel: ProfileScreenViewModel = viewModel(),
    navController: NavController,
    token: String
) {

    val pagerState = rememberPagerState(pageCount = 2)

    val profileToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFullData
    }.collectAsState()

    val userTweets by remember(profileScreenViewModel) {
        profileScreenViewModel.userTweets
    }.collectAsState()

    val userMentions by remember(profileScreenViewModel) {
        profileScreenViewModel.userMentions
    }.collectAsState()

    val profileIdBasedOnName by remember(profileScreenViewModel) {
        profileScreenViewModel.userIdBasedOnUserName
    }.collectAsState()

    ManageProfileScreen(
        profile = profileToDisplay,
        navController = navController,
        pagerState = pagerState,
        tweets = {
            ManageTweets(input = userTweets, navController = navController)
        },
        mentions = {
            ManageMentions(input = userMentions, navController = navController)
        }
    ) {
        /* TODO: FOLLOW ACTION */
    }

    if (!profileID.contains(regex = "[a-zA-Z]+".toRegex())) {
        LaunchedEffect(Unit) {
            profileScreenViewModel.getUserFullDataID(profileID, token)
            profileScreenViewModel.getUserTweets(profileID, token)
            profileScreenViewModel.getUserMentions(profileID, token)
        }
    } else {
        LaunchedEffect(Unit) {
            profileScreenViewModel.getUserIdBasedOnUserName(profileID, token)
        }
        ManageProfileID(
            profileIdBasedOnName,
            profileScreenViewModel,
            token
        )
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun ManageProfileScreen(
    profile: ResponseState<GenericResponse<User?, Includes?, Errors?, Meta?>>,
    navController: NavController,
    pagerState: PagerState,
    tweets: @Composable () -> Unit,
    mentions: @Composable () -> Unit,
    actionFollowUser: (String) -> Unit
) {
    when (profile) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            ProfileFeed(
                user = profile.data,
                navController = navController,
                pagerState = pagerState,
                tweets = { tweets.invoke() },
                mentions = { mentions.invoke() }
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

@ExperimentalCoilApi
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

@ExperimentalCoilApi
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

@ExperimentalCoilApi
@Composable
fun ManageProfileID(
    input: ResponseState<GenericResponse<UserMinimum?, Includes?, Errors?, Meta?>>,
    profileScreenViewModel: ProfileScreenViewModel = viewModel(),
    token: String
) {
    when (input) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            LaunchedEffect(Unit) {
                input.data.outputOne?.id?.let {
                    profileScreenViewModel.getUserFullDataName(
                        it,
                        token
                    )
                }
                input.data.outputOne?.id?.let { profileScreenViewModel.getUserTweets(it, token) }
                input.data.outputOne?.id?.let { profileScreenViewModel.getUserMentions(it, token) }
            }
        }
        is ResponseState.Error -> {
            Text("Profile Mentions Error")
        }
        else -> {
        }
    }
}
