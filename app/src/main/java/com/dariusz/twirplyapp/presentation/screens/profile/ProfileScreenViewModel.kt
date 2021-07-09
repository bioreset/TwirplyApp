package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel
@Inject
constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userInfoFull = MutableStateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>>(ResponseState.Idle)
    val userInfoFull: StateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>> = _userInfoFull

    private var _userInfoCompact = MutableStateFlow<ResponseState<GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>>>(ResponseState.Idle)
    val userInfoCompact: StateFlow<ResponseState<GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>>> = _userInfoCompact



}