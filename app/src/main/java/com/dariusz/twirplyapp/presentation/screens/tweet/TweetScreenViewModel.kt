package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import com.dariusz.twirplyapp.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TweetScreenViewModel
@Inject
constructor(
    private val tweetRepository: TweetRepository
) : ViewModel() {

    private var _fullTweetContent =
        MutableStateFlow<ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val fullTweetContent: StateFlow<ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Meta?>>> =
        _fullTweetContent

    fun getAllTweetData(tweetID: String, token: String) = launchVMTask {
        manageResult(_fullTweetContent, tweetRepository.returnAllTweetInfo(tweetID, token))
    }

}