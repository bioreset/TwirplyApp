package com.dariusz.twirplyapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.dariusz.twirplyapp.domain.model.AppTheme
import com.dariusz.twirplyapp.presentation.components.theme.ThemeSaver
import com.dariusz.twirplyapp.presentation.components.theme.TwirplyAppTheme
import com.dariusz.twirplyapp.presentation.screens.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
            val currentContext = LocalContext.current
            TwirplyAppTheme(theme) {
                SplashScreen {
                    currentContext.startActivity(
                        Intent(currentContext, MainActivity::class.java)
                    )
                }
            }
        }
    }
}
