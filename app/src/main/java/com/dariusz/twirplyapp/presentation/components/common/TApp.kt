package com.dariusz.twirplyapp.presentation.components.common

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dariusz.twirplyapp.di.PreferencesModule.provideAppPreferences
import com.dariusz.twirplyapp.di.RepositoryModule.provideAuthRepository
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.MainViewModelFactory
import com.dariusz.twirplyapp.presentation.components.navigation.BottomBar
import com.dariusz.twirplyapp.presentation.components.navigation.MainNavigationHost
import com.dariusz.twirplyapp.presentation.components.theme.ThemeSaver
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme

@ExperimentalComposeUiApi
@Composable
fun TApp() {
    val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
    val navHostController = rememberNavController()
    val currentContext = LocalContext.current
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            provideAuthRepository(),
            provideAppPreferences(currentContext)
        )
    )

    val tempBearerToken by remember(mainViewModel) {
        mainViewModel.bearerToken
    }.collectAsState()

    Log.e("FETCH_TOKEN", tempBearerToken)

    val loggedInUserID by remember(mainViewModel) {
        mainViewModel.userLoginID
    }.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getBearerTokenAndSaveIt()
    }

    TwirplyAppTheme(theme) {
        MainLayout(
            topBar = { TopBar() },
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