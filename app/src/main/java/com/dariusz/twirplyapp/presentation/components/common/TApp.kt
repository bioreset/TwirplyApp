package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.PreferencesModule.provideAppPreferences
import com.dariusz.twirplyapp.di.RepositoryModule.provideAuthRepository
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.MainViewModelFactory
import com.dariusz.twirplyapp.presentation.components.navigation.BottomBar
import com.dariusz.twirplyapp.presentation.components.navigation.MainNavigationHost
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun TApp() {
    val navHostController = rememberNavController()
    val currentContext = LocalContext.current
    val theme = remember {
        mutableStateOf(
            AppTheme()
        )
    }
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            provideAuthRepository(),
            provideAppPreferences(currentContext)
        )
    )

    val loggedInUserID by remember(mainViewModel) {
        mainViewModel.userLoginID
    }.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getBearerTokenAndSaveIt()
    }

    TwirplyAppTheme(theme.value) {
        MainLayout(
            bottomBar = { BottomBar(navHostController) },
            content = {
                MainNavigationHost(
                    navHostController,
                    loggedInUserID.toLong(),
                    mainViewModel
                )
            }
        )
    }
}