package com.dariusz.twirplyapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.AuthResponseInitial
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _initialResponse = MutableStateFlow(AuthResponseInitial())
    val initialResponse: StateFlow<AuthResponseInitial> = _initialResponse

    fun sendInitialResponse() = launchVMTask {
        _initialResponse.value = authRepository.requestOAuthToken()
    }

}