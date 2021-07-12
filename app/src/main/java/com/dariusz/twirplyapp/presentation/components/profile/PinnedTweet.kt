package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.User
import com.dariusz.twirplyapp.domain.model.UserMinimum.Companion.minimizeUser
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweetSeparate

@Composable
fun PinnedTweet(
    user: GenericResponse<User?, Includes?, Errors?, Nothing>,
    navController: NavController
) {
    val authorInfo = user.outputOne
    val pinnedTweetInfo = user.outputTwo?.tweet?.get(0)
    DisplayTweetSeparate(tweetContent = pinnedTweetInfo, authorInfo = authorInfo?.let {
        minimizeUser(
            it
        )
    }, navController = navController)
}