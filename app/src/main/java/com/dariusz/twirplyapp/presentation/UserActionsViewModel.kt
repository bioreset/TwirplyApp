package com.dariusz.twirplyapp.presentation

import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.domain.model.UserActions
import com.dariusz.twirplyapp.domain.repository.useractions.UserActionsRepository
import com.dariusz.twirplyapp.utils.UserActionUtils.performUserAction
import com.dariusz.twirplyapp.utils.ViewModelUtils.launchVMTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserActionsViewModel
@Inject constructor(
    private val userActionsRepository: UserActionsRepository,
) : ViewModel() {

    private var _actionResult =
        MutableStateFlow(
            UserActions()
        )
    val actionResult: StateFlow<UserActions> =
        _actionResult

    fun actionBlock(userID: String, targetUserID: String, token: String) = launchVMTask {
        performUserAction(
            userActionsRepository.makeBlockUserAction(userID, targetUserID, token) ?: UserActions(),
            userActionsRepository.makeUnblockUserAction(userID, targetUserID, token)
                ?: UserActions(),
            _actionResult
        )
    }

    fun actionLike(userID: String, targetTweetID: String, token: String) = launchVMTask {
        performUserAction(
            userActionsRepository.makeLikeTweetAction(userID, targetTweetID, token)
                ?: UserActions(),
            userActionsRepository.makeUnlikeTweetAction(userID, targetTweetID, token)
                ?: UserActions(),
            _actionResult
        )
    }

    fun actionRetweet(userID: String, targetTweetID: String, token: String) = launchVMTask {
        performUserAction(
            userActionsRepository.makeRetweetAction(userID, targetTweetID, token)
                ?: UserActions(),
            userActionsRepository.makeUnfollowUserAction(userID, targetTweetID, token)
                ?: UserActions(),
            _actionResult
        )
    }

    fun actionFollow(userID: String, targetUserID: String, token: String) = launchVMTask {
        performUserAction(
            userActionsRepository.makeFollowUserAction(userID, targetUserID, token)
                ?: UserActions(),
            userActionsRepository.makeUnfollowUserAction(userID, targetUserID, token)
                ?: UserActions(),
            _actionResult
        )
    }

}
