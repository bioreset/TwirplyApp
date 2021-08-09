package com.dariusz.twirplyapp.presentation.screens.retweeted

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
class WhoRetweetedScreenViewModel
@Inject constructor(
    private val userContextRepository: UserContextRepository
) : ViewModel() {

    private var _whoRetweeted =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val whoRetweeted: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _whoRetweeted

    fun fetchRetweets(tweetID: String, token: String) = launchVMTask {
        manageResult(
            _whoRetweeted,
            userContextRepository.fetchRetweets(tweetID, token)
        )
    }

}