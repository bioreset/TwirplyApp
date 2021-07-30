package com.dariusz.twirplyapp.presentation.screens.feed

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun FeedScreen(
    navController: NavController
) {
    Button(onClick = {
        with(navController) {
            navigateToWithArgument(
                Screens.AppScreens.ProfileScreen.route,
                "2244994945"
            )
        }
    }) {
        Text("Open test profile")
    }
}