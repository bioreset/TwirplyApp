package com.dariusz.twirplyapp.data.remote.api.tweet

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitTweet
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiTweetService {

    suspend fun getTweetDataBasedOnId(
        id: Int
    ): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

    suspend fun fetchTweetTimelineOfUserBasedOnID(
        userID: Int,
        tweetExpansions: String,
        paginationToken: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>

    suspend fun fetchMentionsTimelineOfUserBasedOnID(
        userID: Int,
        tweetExpansions: String,
        paginationToken: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>

}

class TwirplyAppApiTweetServiceImpl : TwirplyAppApiTweetService {

    private val retrofit = provideRetrofitTweet()

    override suspend fun getTweetDataBasedOnId(id: Int) =
        retrofit.fetchTweetDataBasedOnId(id)

    override suspend fun fetchTweetTimelineOfUserBasedOnID(
        userID: Int,
        tweetExpansions: String,
        paginationToken: String
    ) =
        retrofit.fetchTweetTimelineOfUserBasedOnID(userID, tweetExpansions, paginationToken)

    override suspend fun fetchMentionsTimelineOfUserBasedOnID(
        userID: Int,
        tweetExpansions: String,
        paginationToken: String
    ) =
        retrofit.fetchMentionsTimelineOfUserBasedOnID(userID, tweetExpansions, paginationToken)

}