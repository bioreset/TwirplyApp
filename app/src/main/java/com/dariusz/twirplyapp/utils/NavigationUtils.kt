package com.dariusz.twirplyapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavController

object NavigationUtils {

    fun NavController.navigateToWithArgument(
        screenRoute: String,
        argument: String
    ) = navigate(screenRoute.plus("/$argument")) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route)
        }
        launchSingleTop = true
        restoreState = true
    }

    fun openInBrowser(context: Context, url: String) {
        val defaultBrowser =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(url)
        return context.startActivity(defaultBrowser)
    }

}
