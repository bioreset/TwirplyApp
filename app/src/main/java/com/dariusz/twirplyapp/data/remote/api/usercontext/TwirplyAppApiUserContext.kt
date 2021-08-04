package com.dariusz.twirplyapp.data.remote.api.usercontext

import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.utils.Constants.API_EXPANSIONS
import com.dariusz.twirplyapp.utils.Constants.API_MEDIA_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_POLL_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_TWEET_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_USER_FIELDS_COMPACT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwirplyAppApiUserContext {

    @GET("2/tweets/{tweet_id}/retweeted_by")
    suspend fun fetchRetweets(
        @Path("tweet_id") tweetID: String,
        @Query("expansions") tweetExpansions: String? = API_EXPANSIONS,
        @Query("tweet.fields") tweetFields: String? = API_TWEET_FIELDS,
        @Query("poll.fields") tweetPoll: String? = API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = API_MEDIA_FIELDS,
        @Query("user.fields") tweetUser: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

    @GET("2/users/{user_id}/liked_tweets")
    suspend fun fetchLikedTweets(
        @Path("user_id") userID: String,
        @Query("expansions") tweetExpansions: String? = API_EXPANSIONS,
        @Query("tweet.fields") tweetFields: String? = API_TWEET_FIELDS,
        @Query("poll.fields") tweetPoll: String? = API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = API_MEDIA_FIELDS,
        @Query("user.fields") tweetUser: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

    @GET("2/tweets/{tweet_id}/liking_users")
    suspend fun fetchUsersWhoLikedTweet(
        @Path("tweet_id") tweetID: String,
        @Query("user.fields") userFields: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

    @GET("2/users/{user_id}/blocking")
    suspend fun fetchBlockedUsers(
        @Path("user_id") userID: String,
        @Query("user.fields") userFields: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

}