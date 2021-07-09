package com.dariusz.twirplyapp.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.twirplyapp.domain.repository.search.SearchRepository
import javax.inject.Inject

class SearchScreenViewModelFactory
@Inject
constructor(
    private val searchRepository: SearchRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchScreenViewModel(
            searchRepository
        ) as T
    }
}