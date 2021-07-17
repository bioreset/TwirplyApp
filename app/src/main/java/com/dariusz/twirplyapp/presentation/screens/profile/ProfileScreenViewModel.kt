package com.dariusz.twirplyapp.presentation.screens.profile

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
class ProfileScreenViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : ViewModel() {

    private var _userFullData =
        MutableStateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userFullData: StateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Meta?>>> =
        _userFullData

    private var _userFollowing =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userFollowing: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _userFollowing

    private var _userFollowers =
        MutableStateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userFollowers: StateFlow<ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>> =
        _userFollowers

    private var _userTweets =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userTweets: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>> =
        _userTweets

    private var _userMentions =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userMentions: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>> =
        _userMentions

    fun getUserFullData(userID: String, token: String) = viewModelScope.launch {
        _userFullData.value = ResponseState.Loading
        val user = userRepository.returnAllUserInfo(userID, token)
        try {
            _userFullData.value = ResponseState.Success(user)
        } catch (exception: Exception) {
            _userFullData.value = ResponseState.Error(exception)
        }
    }

    fun getUserFollowing(userID: String, token: String) = viewModelScope.launch {
        _userFollowing.value = ResponseState.Loading
        val following = userRepository.fetchUserFollowingBasedOnId(userID, token)
        try {
            _userFollowing.value = ResponseState.Success(following)
        } catch (exception: Exception) {
            _userFollowing.value = ResponseState.Error(exception)
        }
    }

    fun getUserFollowers(userID: String, token: String) = viewModelScope.launch {
        _userFollowers.value = ResponseState.Loading
        val followers = userRepository.fetchUserFollowersBasedOnId(userID, token)
        try {
            _userFollowers.value = ResponseState.Success(followers)
        } catch (exception: Exception) {
            _userFollowers.value = ResponseState.Error(exception)
        }
    }

    fun getUserTweets(userID: String, token: String) = viewModelScope.launch {
        _userTweets.value = ResponseState.Loading
        val userTweets = tweetRepository.returnTweetsOfUser(userID, token = token)
        try {
            _userTweets.value = ResponseState.Success(userTweets)
        } catch (exception: Exception) {
            _userTweets.value = ResponseState.Error(exception)
        }
    }

    fun getUserMentions(userID: String, token: String) = viewModelScope.launch {
        _userMentions.value = ResponseState.Loading
        val userMentions = tweetRepository.returnMentionsOfUser(userID, token = token)
        try {
            _userMentions.value = ResponseState.Success(userMentions)
        } catch (exception: Exception) {
            _userMentions.value = ResponseState.Error(exception)
        }
    }


}