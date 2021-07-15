package com.dariusz.twirplyapp.data.remote.api.search

import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.utils.Constants
import com.dariusz.twirplyapp.utils.Constants.API_TWEET_FIELDS
import retrofit2.http.GET
import retrofit2.http.Query

interface TwirplyAppApiSearch {

    @GET("2/tweets/search/recent?")
    suspend fun fetchRecentTweetsDataBasedOnSearchInput(
        @Query("query") searchQuery: String,
        @Query("expansions") tweetExpansions: String? = Constants.API_EXPANSIONS,
        @Query("tweet.fields") tweetFields: String? = API_TWEET_FIELDS,
        @Query("poll.fields") tweetPoll: String? = Constants.API_POLL_FIELDS,
        @Query("media.fields") tweetMedia: String? = Constants.API_MEDIA_FIELDS,
        @Query("place.fields") tweetPlace: String? = Constants.API_PLACE_FIELDS,
        @Query("user.fields") tweetUser: String = Constants.API_USER_FIELDS_COMPACT
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}