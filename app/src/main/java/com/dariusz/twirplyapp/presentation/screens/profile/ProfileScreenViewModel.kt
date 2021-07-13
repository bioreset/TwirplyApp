package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel
@Inject
constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userFullData =
        MutableStateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>>(
            ResponseState.Idle
        )
    val userFullData: StateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>> =
        _userFullData

    private var _userFollowing =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>>>(
            ResponseState.Idle
        )
    val userFollowing: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>>> =
        _userFollowing

    private var _userFollowers =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>>>(
            ResponseState.Idle
        )
    val userFollowers: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>>> =
        _userFollowers

    fun getUserFullData(userID: Int) = viewModelScope.launch {
        _userFullData.value = ResponseState.Loading
        val user = userRepository.returnAllUserInfo(userID)
        try {
            _userFullData.value = ResponseState.Success(user)
        } catch (exception: Exception) {
            _userFullData.value = ResponseState.Error(exception)
        }
    }

    fun getUserFollowing(userID: Int) = viewModelScope.launch {
        _userFollowing.value = ResponseState.Loading
        val following = userRepository.fetchUserFollowingBasedOnId(userID)
        try {
            _userFollowing.value = ResponseState.Success(following)
        } catch (exception: Exception) {
            _userFollowing.value = ResponseState.Error(exception)
        }
    }

    fun getUserFollowers(userID: Int) = viewModelScope.launch {
        _userFollowers.value = ResponseState.Loading
        val followers = userRepository.fetchUserFollowersBasedOnId(userID)
        try {
            _userFollowers.value = ResponseState.Success(followers)
        } catch (exception: Exception) {
            _userFollowers.value = ResponseState.Error(exception)
        }
    }

}