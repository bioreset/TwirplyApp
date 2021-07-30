package com.dariusz.twirplyapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.dariusz.twirplyapp.di.PreferencesModule_ProvideAppPreferencesFactory.provideAppPreferences
import com.dariusz.twirplyapp.di.RepositoryModule_ProvideAuthRepositoryFactory.provideAuthRepository
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.components.common.OpenLink
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme
import com.dariusz.twirplyapp.presentation.screens.splash.SplashScreen
import com.dariusz.twirplyapp.utils.AuthUtils.OpenUrlForLoginAction
import com.dariusz.twirplyapp.utils.ViewModelUtils.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            val currentContext = LocalContext.current
            val theme = remember { AppTheme() }
            val mainViewModel = viewModel {
                MainViewModel(
                    provideAuthRepository(),
                    provideAppPreferences(currentContext)
                )
            }
            var openBrowser by remember {
                mutableStateOf(false)
            }
            TwirplyAppTheme(theme) {
                SplashScreen(
                    actionLoginWithAccount = {
                        openBrowser = true
                    },
                    actionLoginWithoutAccount = {
                        currentContext.startActivity(
                            Intent(currentContext, MainActivity::class.java)
                        )
                    }
                )
                if (openBrowser) {
                    OpenUrlForLoginAction(
                        { mainViewModel.sendInitialResponse() },
                        mainViewModel.initialResponse.value,
                    ) {
                        OpenLink(it)
                    }
                }
            }
        }
    }

}
