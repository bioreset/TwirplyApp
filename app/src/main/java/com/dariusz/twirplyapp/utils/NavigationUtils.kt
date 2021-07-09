package com.dariusz.twirplyapp.utils

import androidx.navigation.NavController

object NavigationUtils {

    fun navigateToWithArgument(
        navController: NavController,
        screenRoute: String,
        argument: String
    ) {
        navController.navigate(screenRoute.plus("/$argument")) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}