package com.dariusz.twirplyapp.data.remote.api.tweet

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitTweet
import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet

interface TwirplyAppApiTweetService {

    suspend fun getTweetDataBasedOnId(
        id: Int,
        expansions: String
    ): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

}

class TwirplyAppApiTweetServiceImpl : TwirplyAppApiTweetService {

    private val retrofit = provideRetrofitTweet()

    override suspend fun getTweetDataBasedOnId(id: Int, expansions: String) =
        retrofit.fetchTweetDataBasedOnId(id, expansions)

}