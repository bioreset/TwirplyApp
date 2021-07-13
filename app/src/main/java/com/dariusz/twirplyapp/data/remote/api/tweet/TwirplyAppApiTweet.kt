package com.dariusz.twirplyapp.data.remote.api.tweet

import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.utils.Constants.API_EXPANSIONS
import com.dariusz.twirplyapp.utils.Constants.API_MEDIA_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_PLACE_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_POLL_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_TWEET_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_USER_FIELDS_COMPACT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwirplyAppApiTweet {

    @GET("tweets/{id}")
    suspend fun fetchTweetDataBasedOnId(
        @Path("id") tweetID: Int,
        @Query("expansions") tweetExpansions: String = API_EXPANSIONS,
        @Query("tweet.fields") tweetFields: String = API_TWEET_FIELDS,
        @Query("poll.fields") tweetPoll: String? = API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = API_MEDIA_FIELDS,
        @Query("place.fields") tweetPlace: String? = API_PLACE_FIELDS,
        @Query("user.fields") tweetUser: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

    @GET("users/{id}/tweets")
    suspend fun fetchTweetTimelineOfUserBasedOnID(
        @Path("id") userID: Int,
        @Query("pagination_token") paginationToken: String,
        @Query("expansions") tweetExpansions: String = API_EXPANSIONS,
        @Query("tweet.fields") tweetFields: String = API_TWEET_FIELDS,
        @Query("poll.fields") tweetPoll: String? = API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = API_MEDIA_FIELDS,
        @Query("place.fields") tweetPlace: String? = API_PLACE_FIELDS,
        @Query("user.fields") tweetUser: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>

    @GET("users/{id}/mentions")
    suspend fun fetchMentionsTimelineOfUserBasedOnID(
        @Path("id") userID: Int,
        @Query("pagination_token") paginationToken: String,
        @Query("expansions") tweetExpansions: String = API_EXPANSIONS,
        @Query("tweet.fields") tweetFields: String = API_TWEET_FIELDS,
        @Query("poll.fields") tweetPoll: String? = API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = API_MEDIA_FIELDS,
        @Query("place.fields") tweetPlace: String? = API_PLACE_FIELDS,
        @Query("user.fields") tweetUser: String = API_USER_FIELDS_COMPACT
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>

}