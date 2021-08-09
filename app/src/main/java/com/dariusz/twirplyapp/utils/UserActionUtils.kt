package com.dariusz.twirplyapp.utils

import com.dariusz.twirplyapp.domain.model.UserActions
import kotlinx.coroutines.flow.MutableStateFlow

object UserActionUtils {

    fun performUserAction(
        actionOne: UserActions,
        actionTwo: UserActions,
        actionResult: MutableStateFlow<UserActions>
    ) {
        if (actionOne.like == true || actionOne.block == true || actionOne.follow == true || actionOne.retweet == true) {
            actionResult.value = actionOne
        } else {
            actionResult.value = actionTwo
        }
    }


}