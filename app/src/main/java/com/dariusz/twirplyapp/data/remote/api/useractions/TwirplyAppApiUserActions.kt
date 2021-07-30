package com.dariusz.twirplyapp.data.remote.api.useractions

import com.dariusz.twirplyapp.domain.model.UserActions
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TwirplyAppApiUserActions {

    @POST("2/users/{user_id}/retweets")
    suspend fun reTweet(
        @Path("user_id") userID: String,
        @Query("tweet_id") tweetID: String
    ): UserActions?

    @DELETE("2/users/{source_user_id}/retweets/{source_tweet_id}")
    suspend fun undoRetweet(
        @Path("source_user_id") userID: String,
        @Path("source_tweet_id") tweetID: String,
    ): UserActions?

    @POST("2/users/{user_id}/following")
    suspend fun followUser(
        @Path("user_id") userID: String,
        @Query("target_user_id") targetUserID: String
    ): UserActions?

    @DELETE("2/users/{source_user_id}/following/{target_user_id}")
    suspend fun unfollowUser(
        @Path("source_user_id") sourceUserID: String,
        @Path("target_user_id") targetUserID: String
    ): UserActions?

    @POST("2/users/{user_id}/blocking")
    suspend fun blockUser(
        @Path("user_id") userID: String,
        @Query("target_user_id") targetUserID: String
    ): UserActions?

    @DELETE("2/users/{source_user_id}/blocking/{target_user_id}")
    suspend fun unblockUser(
        @Path("source_user_id") sourceUserID: String,
        @Path("target_user_id") targetUserID: String
    ): UserActions?

    @POST("2/users/{user_id}/likes")
    suspend fun likeTweet(
        @Path("user_id") userID: String,
        @Query("tweet_id") targetTweetID: String
    ): UserActions?

    @DELETE("2/users/{user_id}/likes/{tweet_id}")
    suspend fun unlikeTweet(
        @Path("user_id") userID: String,
        @Path("tweet_id") tweetID: String
    ): UserActions?


}