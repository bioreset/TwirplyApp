package com.dariusz.twirplyapp.presentation.screens.splash

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideAuthRepository
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.MainActivity
import com.dariusz.twirplyapp.presentation.components.common.LogInScreen
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme
import com.dariusz.twirplyapp.utils.AuthUtils.OpenUrlForLoginAction
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel

@ExperimentalCoilApi
@Composable
fun SplashScreen(navController: NavController) {
    val currentContext = LocalContext.current
    val theme = remember { AppTheme() }
    val splashScreenViewModel = composeViewModel {
        SplashScreenViewModel(
            provideAuthRepository(),
        )
    }
    TwirplyAppTheme(theme) {
        LogInScreen(
            logInAction = {
                OpenUrlForLoginAction(
                    { splashScreenViewModel.sendInitialResponse() },
                    splashScreenViewModel.initialResponse.value,
                ) {
                    navController.navigateToWithArgument(
                        Screens.AppScreens.SplashScreen.route,
                        it
                    )
                }
            },
            getIntoAppWithoutLoggingIn = {
                currentContext.startActivity(
                    Intent(currentContext, MainActivity::class.java)
                )
            }
        )
    }

}