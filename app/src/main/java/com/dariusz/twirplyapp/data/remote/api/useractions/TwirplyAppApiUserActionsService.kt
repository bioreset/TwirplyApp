package com.dariusz.twirplyapp.data.remote.api.useractions

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitUserActions
import com.dariusz.twirplyapp.domain.model.UserActions

interface TwirplyAppApiUserActionsService {

    suspend fun reTweetAction(
        userID: String,
        tweetID: String,
        token: String
    ): UserActions?

    suspend fun undoRetweetAction(
        userID: String,
        tweetID: String,
        token: String
    ): UserActions?

    suspend fun followUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun unfollowUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun blockUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun unblockUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun likeTweetAction(
        userID: String,
        targetTweetID: String,
        token: String
    ): UserActions?

    suspend fun unlikeTweetAction(
        userID: String,
        tweetID: String,
        token: String
    ): UserActions?

}

class TwirplyAppApiUserActionsServiceImpl : TwirplyAppApiUserActionsService {

    override suspend fun reTweetAction(
        userID: String,
        tweetID: String,
        token: String
    ) = provideRetrofitUserActions(token).reTweet(userID, tweetID)

    override suspend fun undoRetweetAction(
        userID: String,
        tweetID: String,
        token: String
    ) = provideRetrofitUserActions(token).undoRetweet(userID, tweetID)

    override suspend fun followUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ) = provideRetrofitUserActions(token).followUser(userID, targetUserID)

    override suspend fun unfollowUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ) = provideRetrofitUserActions(token).unfollowUser(sourceUserID, targetUserID)

    override suspend fun blockUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ) = provideRetrofitUserActions(token).blockUser(userID, targetUserID)

    override suspend fun unblockUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ) = provideRetrofitUserActions(token).unblockUser(sourceUserID, targetUserID)

    override suspend fun likeTweetAction(
        userID: String,
        targetTweetID: String,
        token: String
    ) = provideRetrofitUserActions(token).likeTweet(userID, targetTweetID)

    override suspend fun unlikeTweetAction(
        userID: String,
        tweetID: String,
        token: String
    ) = provideRetrofitUserActions(token).unlikeTweet(userID, tweetID)

}