package com.dariusz.twirplyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.twirplyapp.data.local.AppPreferences
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepository
import javax.inject.Inject

class MainViewModelFactory
@Inject
constructor(
    private val authRepository: AuthRepository,
    private val appPreferences: AppPreferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            authRepository,
            appPreferences
        ) as T
    }
}