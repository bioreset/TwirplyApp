package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import com.dariusz.twirplyapp.domain.repository.usercontext.UserContextRepository
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import com.dariusz.twirplyapp.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository,
    private val userContextRepository: UserContextRepository
) : ViewModel() {

    private var _userFullData =
        MutableStateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userFullData: StateFlow<ResponseState<GenericResponse<User?, Includes?, Errors?, Meta?>>> =
        _userFullData

    private var _userIdBasedOnUserName =
        MutableStateFlow<ResponseState<GenericResponse<UserMinimum?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val userIdBasedOnUserName: StateFlow<ResponseState<GenericResponse<UserMinimum?, Includes?, Errors?, Meta?>>> =
        _userIdBasedOnUserName

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

    private var _likedTweets =
        MutableStateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>>(
            ResponseState.Idle
        )
    val likedTweets: StateFlow<ResponseState<GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>>> =
        _likedTweets

    fun getUserFullDataName(userID: String, token: String) = launchVMTask {
        manageResult(_userFullData, userRepository.returnAllUserInfo(userID, token))
    }

    fun getUserFullDataID(userName: String, token: String) = launchVMTask {
        manageResult(_userFullData, userRepository.getAllUserDataBasedOnUsername(userName, token))
    }

    fun getUserTweets(userID: String, token: String) = launchVMTask {
        manageResult(_userTweets, tweetRepository.returnTweetsOfUser(userID, token))
    }

    fun getUserMentions(userID: String, token: String) = launchVMTask {
        manageResult(_userMentions, tweetRepository.returnMentionsOfUser(userID, token))
    }

    fun getUserIdBasedOnUserName(userID: String, token: String) = launchVMTask {
        manageResult(
            _userIdBasedOnUserName,
            userRepository.fetchUserIdBasedOnUsername(userID, token)
        )
    }

    fun getUserLiked(userID: String, token: String) = launchVMTask {
        manageResult(
            _likedTweets,
            userContextRepository.fetchLikedTweets(userID, token)
        )
    }


}