package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweetSeparate

@Composable
fun TweetWithAuthorList(
    input: GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>,
    navController: NavController
) {
    val listOfTweets = input.outputOne.orEmpty()
    val listOfUsers = input.outputTwo?.user.orEmpty()
    val includes = input.outputTwo

    LazyColumn {
        items(listOfTweets.zip(listOfUsers)) { item ->
            DisplayTweetSeparate(item.first, item.second, includes, navController)
        }
    }
}