package com.dariusz.twirplyapp.presentation

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
class MainViewModel
@Inject
constructor(
    private val tweetRepository: TweetRepository
) : ViewModel() {

    private var _fullTweetContent =
        MutableStateFlow<ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Nothing>>>(
            ResponseState.Idle
        )
    val fullTweetContent: StateFlow<ResponseState<GenericResponse<Tweet?, Includes?, Errors?, Nothing>>> =
        _fullTweetContent

    private var _userTweets =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>>>(
            ResponseState.Idle
        )
    val userTweets: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>>> =
        _userTweets

    private var _userMentions =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>>>(
            ResponseState.Idle
        )
    val userMentions: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>>> =
        _userMentions


    fun getAllTweetData(tweetID: Int) = viewModelScope.launch {
        _fullTweetContent.value = ResponseState.Loading
        val allTweetData = tweetRepository.returnAllTweetInfo(tweetID)
        try {
            _fullTweetContent.value = ResponseState.Success(allTweetData)
        } catch (exception: Exception) {
            _fullTweetContent.value = ResponseState.Error(exception)
        }
    }

    fun getUserTweets(userID: Int) = viewModelScope.launch {
        _userTweets.value = ResponseState.Loading
        val userTweets = tweetRepository.returnTweetsOfUser(userID)
        try {
            _userTweets.value = ResponseState.Success(userTweets)
        } catch (exception: Exception) {
            _userTweets.value = ResponseState.Error(exception)
        }
    }

    fun getUserMentions(userID: Int) = viewModelScope.launch {
        _userMentions.value = ResponseState.Loading
        val userMentions = tweetRepository.returnMentionsOfUser(userID)
        try {
            _userTweets.value = ResponseState.Success(userMentions)
        } catch (exception: Exception) {
            _userTweets.value = ResponseState.Error(exception)
        }
    }

}





