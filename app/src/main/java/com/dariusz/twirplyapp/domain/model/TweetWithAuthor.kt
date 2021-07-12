package com.dariusz.twirplyapp.domain.model

data class TweetWithAuthor(
    val responseWithTweet: GenericResponse<Tweet?, Includes?, Errors?, Nothing>,
    val responseWithAuthor: GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>
)
