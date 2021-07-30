package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
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
    private val tweetRepository: TweetRepository
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

    fun getUserFullDataName(userID: String, token: String) = launchVMTask {
        manageResult(_userFullData, userRepository.returnAllUserInfo(userID, token))
    }

    fun getUserFullDataID(userName: String, token: String) = launchVMTask {
        manageResult(_userFullData, userRepository.getAllUserDataBasedOnUsername(userName, token))
    }

    fun getUserFollowing(userID: String, token: String) = launchVMTask {
        manageResult(_userFollowing, userRepository.fetchUserFollowingBasedOnId(userID, token))
    }

    fun getUserFollowers(userID: String, token: String) = launchVMTask {
        manageResult(_userFollowers, userRepository.fetchUserFollowersBasedOnId(userID, token))
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
}