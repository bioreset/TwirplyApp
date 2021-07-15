package com.dariusz.twirplyapp.presentation.screens.tweet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun getAllTweetData(tweetID: String, token: String) = viewModelScope.launch {
        _fullTweetContent.value = ResponseState.Loading
        val allTweetData = tweetRepository.returnAllTweetInfo(tweetID, token)
        try {
            _fullTweetContent.value = ResponseState.Success(allTweetData)
        } catch (exception: Exception) {
            _fullTweetContent.value = ResponseState.Error(exception)
        }
    }

}