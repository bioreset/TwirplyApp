package com.dariusz.twirplyapp.presentation.screens.feed

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun FeedScreen(
    profileID: String,
    navController: NavController
) {

    if (profileID.toInt() == 0) Text("Feed Screen")
    else Text("wtf?")


    Button(onClick = {
        navigateToWithArgument(
            navController,
            Screens.AppScreens.ProfileScreen.route,
            "2244994945"
        )
    }) {
        Text("Open test profile")
    }
}