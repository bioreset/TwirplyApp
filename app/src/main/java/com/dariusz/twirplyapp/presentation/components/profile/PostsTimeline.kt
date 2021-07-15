package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweetSeparate

@Composable
fun UserTweetsTimeline(
    input: GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>,
    navController: NavController
) {

    val listOfTweets = input.outputOne.orEmpty()
    val user = input.outputTwo?.user?.get(0)
    val includesOflist = input.outputTwo

    LazyColumn {
        items(listOfTweets) { item ->
            DisplayTweetSeparate(item, user, includesOflist, navController)
        }
    }
}

@Composable
fun UserMentionsTimeline(
    input: GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>,
    navController: NavController
) {
    val listOfTweets = input.outputOne.orEmpty()
    val listOfUsers = input.outputTwo?.user.orEmpty()
    val includesOflist = input.outputTwo

    LazyColumn {
        items(listOfTweets.zip(listOfUsers)) { item ->
            DisplayTweetSeparate(item.first, item.second, includesOflist, navController)
        }
    }
}

