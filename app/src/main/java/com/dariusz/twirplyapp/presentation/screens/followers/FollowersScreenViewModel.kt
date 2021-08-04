package com.dariusz.twirplyapp.presentation.screens.followers

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import com.dariusz.twirplyapp.utils.ViewModelUtils.manageResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FollowersScreenViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userFollowers =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userFollowers: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _userFollowers

    fun getUserFollowers(userID: String, token: String) = launchVMTask {
        manageResult(
            _userFollowers,
            userRepository.fetchUserFollowersBasedOnId(userID, token)
        )
    }

}