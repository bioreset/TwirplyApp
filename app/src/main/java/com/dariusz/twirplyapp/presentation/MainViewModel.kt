package com.dariusz.twirplyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.twirplyapp.data.local.AppPreferences
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val authRepository: AuthRepository,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private var _userLoginStatus = MutableStateFlow(
        false
    )
    val userLoginStatus: StateFlow<Boolean> =
        _userLoginStatus

    private var _userLoginID = MutableStateFlow(
        0
    )
    val userLoginID: StateFlow<Int> =
        _userLoginID

    private val _bearerToken = MutableStateFlow(
        ""
    )
    val bearerToken: StateFlow<String> = _bearerToken

    fun getBearerTokenAndSaveIt() = viewModelScope.launch {
        val response = authRepository.fetchBearerTokenAndSaveIt()
        appPreferences.let {
            response.accessToken?.let { it1 -> it.saveBearerToken(it1) }
            it.getBearerToken().collect { it2 ->
                _bearerToken.value = it2
            }
        }

    }

    fun getUserLoginID() = viewModelScope.launch {
        appPreferences.getIDOfLoggedInUser().collect {
            _userLoginID.value = it
        }
    }

    fun getUserLoginStatus() = viewModelScope.launch {
        appPreferences.getLoginStatus().collect {
            _userLoginStatus.value = it
        }
    }

    fun logout() = viewModelScope.launch {
        authRepository.invalidateCurrentBearerToken(_bearerToken.value)
    }


}