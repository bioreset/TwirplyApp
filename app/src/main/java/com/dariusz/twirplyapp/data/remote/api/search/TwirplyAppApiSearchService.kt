package com.dariusz.twirplyapp.data.remote.api.search

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofit
import com.dariusz.twirplyapp.domain.model.*
import kotlin.Error

interface TwirplyAppApiSearchService {

    suspend fun getTweetsDataBasedOnSearchInput(input: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class TwirplyAppApiSearchServiceImpl : TwirplyAppApiSearchService {

    private val retrofit = provideRetrofit(TwirplyAppApiSearch::class.java)

    override suspend fun getTweetsDataBasedOnSearchInput(input: String) =
        retrofit.fetchTweetsDataBasedOnSearchInput(input)

}