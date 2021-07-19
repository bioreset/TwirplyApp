package com.dariusz.twirplyapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavController

object NavigationUtils {

    fun navigateToWithArgument(
        navController: NavController,
        screenRoute: String,
        argument: String
    ) = navController.navigate(screenRoute.plus("/$argument")) {
        launchSingleTop = true
    }


    fun openInBrowser(context: Context, url: String) {
        val defaultBrowser =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(url)
        return context.startActivity(defaultBrowser)
    }
}