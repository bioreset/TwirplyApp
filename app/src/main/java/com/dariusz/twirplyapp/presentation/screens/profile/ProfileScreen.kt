package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideTweetRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.profile.LikedPostsList
import com.dariusz.twirplyapp.presentation.components.profile.ProfileFeed
import com.dariusz.twirplyapp.presentation.components.profile.UserMentionsTimeline
import com.dariusz.twirplyapp.presentation.components.profile.UserTweetsTimeline
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun ProfileScreen(
    profileID: String,
    navController: NavController,
    token: String,
) {

    val profileScreenViewModel = composeViewModel {
        ProfileScreenViewModel(
            provideUserRepository(),
            provideTweetRepository(),
            provideUserContextRepository()
        )
    }

    val pagerState: PagerState = rememberPagerState(pageCount = 2)

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

    val userLiked by remember(profileScreenViewModel) {
        profileScreenViewModel.likedTweets
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
        },
        liked = {
            ManageLiked(input = userLiked, navController = navController)
        }
    ) {
        /* TODO: FOLLOW ACTION */
    }

    if (!profileID.contains(regex = "[a-zA-Z]+".toRegex())) {
        LaunchedEffect(Unit) {
            profileScreenViewModel.getUserFullDataID(profileID, token)
            profileScreenViewModel.getUserTweets(profileID, token)
            profileScreenViewModel.getUserMentions(profileID, token)
            profileScreenViewModel.getUserLiked(profileID, token)
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
    liked: @Composable () -> Unit,
    actionFollowUser: (String) -> Unit
) {

    ManageResponseOnScreen(input = profile) {
        ProfileFeed(
            user = it,
            navController = navController,
            pagerState = pagerState,
            tweets = { tweets.invoke() },
            mentions = { mentions.invoke() },
            liked = { liked.invoke() }
        ) { userID ->
            actionFollowUser.invoke(userID)
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
fun ManageLiked(
    input: ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>,
    navController: NavController
) {

    ManageResponseOnScreen(input = input) {
        LikedPostsList(input = it, navController = navController)
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
            it.outputOne?.id?.let { profileScreenViewModel.getUserLiked(it, token) }
        }
    }

}
