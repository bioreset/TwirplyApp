package com.dariusz.twirplyapp.presentation.screens.likes

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
class WhoLikedTweetScreenViewModel
@Inject constructor(
    private val userContextRepository: UserContextRepository
) : ViewModel() {

    private var _whoLikedTweet =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val whoLikedTweet: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _whoLikedTweet

    fun getUsersWhoLikedTweet(tweetID: String, token: String) = launchVMTask {
        manageResult(
            _whoLikedTweet,
            userContextRepository.fetchUsersWhoLikedTweet(tweetID, token)
        )
    }

}