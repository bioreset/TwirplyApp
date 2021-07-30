package com.dariusz.twirplyapp.domain.repository.useractions

import com.dariusz.twirplyapp.data.remote.api.useractions.TwirplyAppApiUserActionsService
import com.dariusz.twirplyapp.domain.model.UserActions
import javax.inject.Inject

interface UserActionsRepository {

    suspend fun makeRetweetAction(
        userID: String,
        tweetID: String,
        token: String
    ): UserActions?

    suspend fun makeUndoRetweetAction(
        userID: String,
        tweetID: String,
        token: String
    ): UserActions?

    suspend fun makeFollowUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun makeUnfollowUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun makeBlockUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun makeUnblockUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ): UserActions?

    suspend fun makeLikeTweetAction(
        userID: String,
        targetTweetID: String,
        token: String
    ): UserActions?

    suspend fun makeUnlikeTweetAction(
        userID: String,
        tweetID: String,
        token: String
    ): UserActions?

}

class UserActionsRepositoryImpl
@Inject constructor(
    private val apiUserActionsService: TwirplyAppApiUserActionsService
) : UserActionsRepository {

    override suspend fun makeRetweetAction(
        userID: String,
        tweetID: String,
        token: String
    ) = apiUserActionsService.reTweetAction(userID, tweetID, token)

    override suspend fun makeUndoRetweetAction(
        userID: String,
        tweetID: String,
        token: String
    ) = apiUserActionsService.undoRetweetAction(userID, tweetID, token)

    override suspend fun makeFollowUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ) = apiUserActionsService.followUserAction(userID, targetUserID, token)

    override suspend fun makeUnfollowUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ) = apiUserActionsService.unfollowUserAction(sourceUserID, targetUserID, token)

    override suspend fun makeBlockUserAction(
        userID: String,
        targetUserID: String,
        token: String
    ) = apiUserActionsService.blockUserAction(userID, targetUserID, token)

    override suspend fun makeUnblockUserAction(
        sourceUserID: String,
        targetUserID: String,
        token: String
    ) = apiUserActionsService.unblockUserAction(sourceUserID, targetUserID, token)

    override suspend fun makeLikeTweetAction(
        userID: String,
        targetTweetID: String,
        token: String
    ) = apiUserActionsService.likeTweetAction(userID, targetTweetID, token)

    override suspend fun makeUnlikeTweetAction(
        userID: String,
        tweetID: String,
        token: String
    ) = apiUserActionsService.unlikeTweetAction(userID, tweetID, token)

}