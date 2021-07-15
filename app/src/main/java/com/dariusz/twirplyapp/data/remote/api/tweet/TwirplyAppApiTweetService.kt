package com.dariusz.twirplyapp.data.remote.api.tweet

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitTweet
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiTweetService {

    suspend fun getTweetDataBasedOnId(
        id: String,
        token: String
    ): GenericResponse<Tweet?, Includes?, Errors?, Meta?>

    suspend fun fetchTweetTimelineOfUserBasedOnID(
        userID: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

    suspend fun fetchMentionsTimelineOfUserBasedOnID(
        userID: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class TwirplyAppApiTweetServiceImpl : TwirplyAppApiTweetService {

    override suspend fun getTweetDataBasedOnId(id: String, token: String) =
        provideRetrofitTweet(token).fetchTweetDataBasedOnId(id)

    override suspend fun fetchTweetTimelineOfUserBasedOnID(
        userID: String,
        token: String
    ) =
        provideRetrofitTweet(token).fetchTweetTimelineOfUserBasedOnID(userID)

    override suspend fun fetchMentionsTimelineOfUserBasedOnID(
        userID: String,
        token: String
    ) =
        provideRetrofitTweet(token).fetchMentionsTimelineOfUserBasedOnID(userID)

}