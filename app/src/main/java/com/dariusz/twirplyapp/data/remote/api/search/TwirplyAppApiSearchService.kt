package com.dariusz.twirplyapp.data.remote.api.search

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitSearch
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiSearchService {

    suspend fun getRecentTweetsDataBasedOnSearchInput(
        input: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class TwirplyAppApiSearchServiceImpl : TwirplyAppApiSearchService {

    override suspend fun getRecentTweetsDataBasedOnSearchInput(input: String, token: String) =
        provideRetrofitSearch(token).fetchRecentTweetsDataBasedOnSearchInput(input)

}