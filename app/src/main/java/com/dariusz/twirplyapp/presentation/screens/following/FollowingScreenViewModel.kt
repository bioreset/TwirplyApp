package com.dariusz.twirplyapp.presentation.screens.following

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import com.dariusz.twirplyapp.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FollowingScreenViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userFollowing =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userFollowing: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _userFollowing

    fun getUserFollowing(userID: String, token: String) = launchVMTask {
        manageResult(
            _userFollowing,
            userRepository.fetchUserFollowingBasedOnId(userID, token)
        )
    }

}