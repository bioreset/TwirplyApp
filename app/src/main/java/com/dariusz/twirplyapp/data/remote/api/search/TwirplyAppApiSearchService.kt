package com.dariusz.twirplyapp.data.remote.api.search

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitSearch
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiSearchService {

    suspend fun getRecentTweetsDataBasedOnSearchInput(input: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

    suspend fun getAllTweetsDataBasedOnSearchInput(input: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class TwirplyAppApiSearchServiceImpl : TwirplyAppApiSearchService {

    private val retrofit = provideRetrofitSearch()

    override suspend fun getRecentTweetsDataBasedOnSearchInput(input: String) =
        retrofit.fetchRecentTweetsDataBasedOnSearchInput(input)

    override suspend fun getAllTweetsDataBasedOnSearchInput(input: String) =
        retrofit.fetchRecentTweetsDataBasedOnSearchInput(input)
}