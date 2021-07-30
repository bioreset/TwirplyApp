package com.dariusz.twirplyapp.domain.repository.usercontext

import com.dariusz.twirplyapp.data.remote.api.usercontext.TwirplyAppApiUserContextService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface UserContextRepository {

    suspend fun fetchRetweets(
        tweetID: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

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

class UserContextRepositoryImpl
@Inject constructor(
    private val apiUserContextService: TwirplyAppApiUserContextService
) : UserContextRepository {

    override suspend fun fetchRetweets(
        tweetID: String,
        token: String
    ) = apiUserContextService.fetchRetweets(tweetID, token)

    override suspend fun fetchLikedTweets(
        userID: String,
        token: String
    ) = apiUserContextService.fetchLikedTweets(userID, token)

    override suspend fun fetchUsersWhoLikedTweet(
        tweetID: String,
        token: String
    ) = apiUserContextService.fetchUsersWhoLikedTweet(tweetID, token)

    override suspend fun fetchBlockedUsers(
        userID: String,
        token: String
    ) = apiUserContextService.fetchBlockedUsers(userID, token)

}