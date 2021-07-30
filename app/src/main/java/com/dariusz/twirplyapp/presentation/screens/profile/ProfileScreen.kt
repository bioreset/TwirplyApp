package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.profile.ProfileFeed
import com.dariusz.twirplyapp.presentation.components.profile.UserMentionsTimeline
import com.dariusz.twirplyapp.presentation.components.profile.UserTweetsTimeline
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun ProfileScreen(
    profileID: String,
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel,
    token: String,
    pagerState: PagerState = rememberPagerState(pageCount = 2)
) {

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
        ManageProfileID(
            profileIdBasedOnName,
            profileScreenViewModel,
            token
        )
        LaunchedEffect(Unit) {
            profileScreenViewModel.getUserIdBasedOnUserName(profileID, token)
        }
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

    ManageResponseOnScreen(input = profile) {
        ProfileFeed(
            user = it,
            navController = navController,
            pagerState = pagerState,
            tweets = { tweets.invoke() },
            mentions = { mentions.invoke() }
        ) {
            actionFollowUser.invoke(it)
        }
    }

}

@ExperimentalCoilApi
@Composable
fun ManageTweets(
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
    navController: NavController
) {

    ManageResponseOnScreen(input = input) {
        UserTweetsTimeline(input = it, navController = navController)
    }

}

@ExperimentalCoilApi
@Composable
fun ManageMentions(
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
    navController: NavController
) {

    ManageResponseOnScreen(input = input) {
        UserMentionsTimeline(input = it, navController = navController)
    }

}

@ExperimentalCoilApi
@Composable
fun ManageProfileID(
    input: ResponseState<GenericResponse<UserMinimum?, Includes?, Errors?, Meta?>>,
    profileScreenViewModel: ProfileScreenViewModel,
    token: String
) {
    ManageResponseOnScreen(input = input) {
        LaunchedEffect(Unit) {
            it.outputOne?.id?.let {
                profileScreenViewModel.getUserFullDataName(
                    it,
                    token
                )
            }
            it.outputOne?.id?.let { profileScreenViewModel.getUserTweets(it, token) }
            it.outputOne?.id?.let { profileScreenViewModel.getUserMentions(it, token) }
        }
    }

}
