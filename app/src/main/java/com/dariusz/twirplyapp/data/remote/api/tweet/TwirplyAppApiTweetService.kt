package com.dariusz.twirplyapp.data.remote.api.tweet

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofit
import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet

interface TwirplyAppApiTweetService {

    suspend fun getTweetDataBasedOnId(id: Int): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

}

class TwirplyAppApiTweetServiceImpl : TwirplyAppApiTweetService {

    private val retrofit = provideRetrofit(TwirplyAppApiTweet::class.java)

    override suspend fun getTweetDataBasedOnId(id: Int) = retrofit.fetchTweetDataBasedOnId(id)

}