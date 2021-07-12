package com.dariusz.twirplyapp.presentation.components.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.model.UserMinimum.Companion.minimizeUser
import com.dariusz.twirplyapp.presentation.components.tweets.DisplayTweetSeparate

@Composable
fun SearchResultsList(
    response: GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>,
    navController: NavController
) {
    val listOfTweets = response.outputOne.orEmpty()
    val listOfUsers = response.outputTwo?.user.orEmpty()

    LazyColumn {
        items(listOfTweets.zip(listOfUsers)) { item ->
            DisplayTweetSeparate(item.first, minimizeUser(item.second), navController)
        }
    }

}
