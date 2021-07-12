package com.dariusz.twirplyapp.data.remote.api.tweet

import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.utils.Constants.API_MEDIA_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_POLL_FIELDS
import com.dariusz.twirplyapp.utils.Constants.API_TWEET_FIELDS_FULL
import com.dariusz.twirplyapp.utils.Constants.API_USER_FIELDS_COMPACT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwirplyAppApiTweet {

    @GET("tweets/{id}")
    suspend fun fetchTweetDataBasedOnId(
        @Path("id") tweetID: Int,
        @Query("expansions") tweetExpansions: String,
        @Query("tweet.fields") tweetFields: String = API_TWEET_FIELDS_FULL,
        @Query("poll.fields") tweetPoll: String? = API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = API_MEDIA_FIELDS,
        @Query("user.fields") tweetUser: String = API_USER_FIELDS_COMPACT,
    ): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

}