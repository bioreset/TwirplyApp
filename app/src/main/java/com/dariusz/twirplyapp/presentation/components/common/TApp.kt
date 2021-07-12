package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.components.navigation.BottomBar
import com.dariusz.twirplyapp.presentation.components.navigation.MainNavigationHost
import com.dariusz.twirplyapp.presentation.components.theme.ThemeSaver
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme

@ExperimentalComposeUiApi
@Composable
fun TApp() {
    val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
    val navHostController = rememberNavController()
    TwirplyAppTheme(theme) {
        MainLayout(
            topBar = { TopBar() },
            bottomBar = { BottomBar(navHostController) },
            content = { MainNavigationHost(navHostController) }
        )
    }
}