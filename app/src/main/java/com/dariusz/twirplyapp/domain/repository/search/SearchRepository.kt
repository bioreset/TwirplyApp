package com.dariusz.twirplyapp.domain.repository.search

import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface SearchRepository {

    suspend fun returnSearchResults(query: String): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class SearchRepositoryImpl
@Inject constructor(
    private val apiSearchService: TwirplyAppApiSearchService
) : SearchRepository {

    override suspend fun returnSearchResults(query: String) =
        apiSearchService.getTweetsDataBasedOnSearchInput(query)

}