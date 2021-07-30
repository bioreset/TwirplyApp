package com.dariusz.twirplyapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import com.dariusz.twirplyapp.presentation.components.common.LogInScreen

@Composable
fun SplashScreen(
    actionLoginWithAccount: () -> Unit,
    actionLoginWithoutAccount: () -> Unit
) {

    LogInScreen(
        logInAction = {
            actionLoginWithAccount.invoke()
        },
        getIntoAppWithoutLoggingIn = {
            actionLoginWithoutAccount.invoke()
        }
    )

}