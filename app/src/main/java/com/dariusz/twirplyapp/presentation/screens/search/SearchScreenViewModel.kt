package com.dariusz.twirplyapp.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun fetchSearchResultsForQuery(query: String, token: String) = viewModelScope.launch {
        _searchResults.value = ResponseState.Loading
        val results = searchRepository.returnRecentSearchResults(query, token)
        try {
            _searchResults.value = ResponseState.Success(results)
        } catch (exception: Exception) {
            _searchResults.value = ResponseState.Error(exception)
        }
    }

}