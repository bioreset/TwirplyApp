package com.dariusz.twirplyapp.data.remote.api.user

import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.utils.Constants.API_TWEET_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_USER_FIELDS_COMPACT
import com.dariusz.twirplyapp.utils.Constants.API_USER_FIELDS_FULL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwirplyAppApiUser {

    @GET("users/{id}")
    suspend fun fetchAllUserDataBasedOnId(
        @Path("id") userID: Int,
        @Query("user.fields") userFields: String = API_USER_FIELDS_FULL,
        @Query("expansions") userExpansions: String = "pinned_tweet_id",
        @Query("tweet.fields") tweetUserFields: String? = API_TWEET_FIELDS,
    ): GenericResponse<User?, Includes?, Errors?, Nothing>

    @GET("users/{id}")
    suspend fun fetchCompactUserDataBasedOnId(
        @Path("id") userID: Int,
        @Query("user.fields") userFields: String = API_USER_FIELDS_COMPACT,
    ): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

    @GET("users/{username}")
    suspend fun fetchAllUserDataBasedOnUsername(
        @Path("username") userName: String,
        @Query("user.fields") userFields: String = API_USER_FIELDS_FULL,
        @Query("expansions") userExpansions: String = "pinned_tweet_id",
        @Query("tweet.fields") tweetUserFields: String? = API_TWEET_FIELDS,
    ): GenericResponse<User?, Includes?, Errors?, Nothing>

    @GET("users/{username}")
    suspend fun fetchCompactUserDataBasedOnUsername(
        @Path("username") userName: String,
        @Query("user.fields") userFields: String = API_USER_FIELDS_COMPACT,
    ): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

    @GET("users/{id}/followers")
    suspend fun fetchUserFollowersBasedOnId(
        @Path("id") userID: Int,
        @Query("user.fields") userFields: String = API_USER_FIELDS_COMPACT,
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>

    @GET("users/{id}/following")
    suspend fun fetchUserFollowingBasedOnId(
        @Path("id") userID: Int,
        @Query("user.fields") userFields: String = API_USER_FIELDS_COMPACT,
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>


}