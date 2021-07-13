package com.dariusz.twirplyapp.domain.repository.search

import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface SearchRepository {

    suspend fun returnRecentSearchResults(query: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

    suspend fun returnAllSearchResults(query: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class SearchRepositoryImpl
@Inject constructor(
    private val apiSearchService: TwirplyAppApiSearchService
) : SearchRepository {

    override suspend fun returnRecentSearchResults(query: String) =
        apiSearchService.getRecentTweetsDataBasedOnSearchInput(query)

    override suspend fun returnAllSearchResults(query: String) =
        apiSearchService.getAllTweetsDataBasedOnSearchInput(query)

}