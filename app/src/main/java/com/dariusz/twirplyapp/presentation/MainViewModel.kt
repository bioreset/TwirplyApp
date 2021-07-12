package com.dariusz.twirplyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val tweetRepository: TweetRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userFullData =
        MutableStateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>>(
            ResponseState.Idle
        )
    val userFullData: StateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>> =
        _userFullData

    private var _fullTweetContent =
        MutableStateFlow<ResponseState<TweetWithAuthor>>(
            ResponseState.Idle
        )
    val fullTweetContent: StateFlow<ResponseState<TweetWithAuthor>> =
        _fullTweetContent


    fun getUserFullData(userID: Int) = viewModelScope.launch {
        _userFullData.value = ResponseState.Loading
        val user = userRepository.returnAllUserInfo(userID)
        try {
            _userFullData.value = ResponseState.Success(user)
        } catch (exception: Exception) {
            _userFullData.value = ResponseState.Error(exception)
        }
    }

    fun getAllTweetData(tweetID: Int) = viewModelScope.launch {
        _fullTweetContent.value = ResponseState.Loading
        val allTweetData = tweetRepository.returnAllTweetInfo(tweetID)
        try {
            _fullTweetContent.value = ResponseState.Success(allTweetData)
        } catch (exception: Exception) {
            _fullTweetContent.value = ResponseState.Error(exception)
        }
    }
}





