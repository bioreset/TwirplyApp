package com.dariusz.twirplyapp.presentation.screens.blocked

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.usercontext.UserContextRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import com.dariusz.twirplyapp.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BlockedUsersScreenViewModel
@Inject constructor(
    private val userContextRepository: UserContextRepository
) : ViewModel() {

    private var _blockedUsers =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val blockedUsers: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _blockedUsers

    fun getBlockedUsers(userID: String, token: String) = launchVMTask {
        manageResult(
            _blockedUsers,
            userContextRepository.fetchBlockedUsers(userID, token)
        )
    }

}