package com.dariusz.twirplyapp.presentation.screens.feed

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
class FeedScreenViewModel
@Inject
constructor(
    private val tweetRepository: TweetRepository
) : ViewModel() {

    private var _tweetContent = MutableStateFlow<ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Nothing>>>(
        ResponseState.Idle)
    val tweetContent: StateFlow<ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Nothing>>> = _tweetContent

    fun getTweetData(tweetID: Int) = viewModelScope.launch {
        _tweetContent.value = ResponseState.Loading
        val tweet = tweetRepository.returnAllTweetInfo(tweetID)
        try {
            _tweetContent.value = ResponseState.Success(tweet)
        }
        catch (exception: Exception){
            _tweetContent.value = ResponseState.Error(exception)
        }
    }

}