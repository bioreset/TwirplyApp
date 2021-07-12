package com.dariusz.twirplyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.content.ContextCompat
import com.dariusz.twirplyapp.presentation.components.common.TApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        setContent {
            TApp()
        }
    }
}
