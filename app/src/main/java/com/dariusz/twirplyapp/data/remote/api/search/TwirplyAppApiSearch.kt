package com.dariusz.twirplyapp.data.remote.api.search

import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.utils.Constants.API_TWEET_FIELDS_FULL
import retrofit2.http.GET
import retrofit2.http.Query

interface TwirplyAppApiSearch {

    @GET("/tweets/search/recent?")
    suspend fun fetchTweetsDataBasedOnSearchInput(
        @Query("query") searchQuery: String,
        @Query("tweet.fields") searchQueryTweetFields: String? = API_TWEET_FIELDS_FULL
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}