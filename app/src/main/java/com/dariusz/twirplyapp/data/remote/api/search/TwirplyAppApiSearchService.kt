package com.dariusz.twirplyapp.data.remote.api.search

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitSearch
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiSearchService {

    suspend fun getTweetsDataBasedOnSearchInput(input: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class TwirplyAppApiSearchServiceImpl : TwirplyAppApiSearchService {

    private val retrofit = provideRetrofitSearch()

    override suspend fun getTweetsDataBasedOnSearchInput(input: String) =
        retrofit.fetchTweetsDataBasedOnSearchInput(input)

}