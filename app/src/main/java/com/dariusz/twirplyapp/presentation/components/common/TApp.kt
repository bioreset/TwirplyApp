package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.PreferencesModule.provideAppPreferences
import com.dariusz.twirplyapp.di.RepositoryModule.provideAuthRepository
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.components.navigation.BottomBar
import com.dariusz.twirplyapp.presentation.components.navigation.MainNavigationHost
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme
import com.dariusz.twirplyapp.utils.ViewModelUtils.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun TApp() {
    val navHostController = rememberNavController()
    val currentContext = LocalContext.current
    val theme = remember { AppTheme() }
    val mainViewModel = viewModel {
        MainViewModel(
            provideAuthRepository(),
            provideAppPreferences(currentContext)
        )
    }

    LaunchedEffect(Unit) {
        mainViewModel.getBearerTokenAndSaveIt()
    }

    TwirplyAppTheme(theme) {
        MainLayout(
            bottomBar = { BottomBar(navHostController) },
            content = {
                MainNavigationHost(
                    navHostController,
                    mainViewModel
                )
            }
        )
    }
}