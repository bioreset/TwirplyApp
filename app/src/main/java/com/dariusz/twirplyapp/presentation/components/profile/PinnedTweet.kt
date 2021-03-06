package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.model.UserMinimum.Companion.minimizeUser
import com.dariusz.twirplyapp.presentation.components.tweet.DisplayTweetSeparate

@ExperimentalCoilApi
@Composable
fun PinnedTweet(
    user: GenericResponse<User?, Includes?, Errors?, Meta?>,
    navController: NavController
) {

    val authorInfo = user.outputOne
    val pinnedTweetInfo = user.outputTwo?.tweet?.get(0)
    val includes = user.outputTwo

    DisplayTweetSeparate(
        tweetContent = pinnedTweetInfo,
        authorInfo = authorInfo?.let {
            minimizeUser(
                it
            )
        },
        includesFromResponse = includes, navController = navController
    )
}