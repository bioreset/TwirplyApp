package com.dariusz.twirplyapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import com.dariusz.twirplyapp.presentation.components.common.LogInScreen

@Composable
fun SplashScreen(
    action: () -> Unit
) {

    LogInScreen(
        logInAction = {

        },
        getIntoAppWithoutLoggingIn = { action.invoke() }
    )

}