package com.dariusz.twirplyapp.presentation.screens.retweets

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
class RetweetsViewModel
@Inject constructor(
    private val userContextRepository: UserContextRepository
) : ViewModel() {

    private var _retweetList =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val retweetList: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>> =
        _retweetList

    fun getRetweetsOfTweet(tweetID: String, token: String) = launchVMTask {
        manageResult(
            _retweetList,
            userContextRepository.fetchRetweets(tweetID, token)
        )
    }


}