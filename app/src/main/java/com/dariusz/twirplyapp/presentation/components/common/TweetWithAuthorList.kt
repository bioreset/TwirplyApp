package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweetSeparate

@ExperimentalCoilApi
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
            val authorOfTweet = listOfUsers.find {
                item.first.authorID == it.id
            }
            DisplayTweetSeparate(item.first, authorOfTweet, includes, navController)
        }
    }
}