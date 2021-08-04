package com.dariusz.twirplyapp.data.remote.api.usercontext

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitUserContext
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiUserContextService {

    suspend fun fetchRetweets(
        tweetID: String,
        token: String
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

    suspend fun fetchLikedTweets(
        userID: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

    suspend fun fetchUsersWhoLikedTweet(
        tweetID: String,
        token: String
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

    suspend fun fetchBlockedUsers(
        userID: String,
        token: String
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

}

class TwirplyAppApiUserContextServiceImpl : TwirplyAppApiUserContextService {

    override suspend fun fetchRetweets(
        tweetID: String,
        token: String
    ) = provideRetrofitUserContext(token).fetchRetweets(tweetID)

    override suspend fun fetchLikedTweets(
        userID: String,
        token: String
    ) = provideRetrofitUserContext(token).fetchLikedTweets(userID)

    override suspend fun fetchUsersWhoLikedTweet(
        tweetID: String,
        token: String
    ) = provideRetrofitUserContext(token).fetchUsersWhoLikedTweet(tweetID)

    override suspend fun fetchBlockedUsers(
        userID: String,
        token: String
    ) = provideRetrofitUserContext(token).fetchBlockedUsers(userID)


}