package com.dariusz.twirplyapp.presentation.screens.search

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.search.SearchRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import com.dariusz.twirplyapp.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel
@Inject
constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private var _searchResults =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val finalSearchResults: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>> =
        _searchResults

    fun fetchSearchResultsForQuery(query: String, token: String) = launchVMTask {
        manageResult(_searchResults, searchRepository.returnRecentSearchResults(query, token))
    }

}