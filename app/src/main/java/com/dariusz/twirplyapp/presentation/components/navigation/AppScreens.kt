package com.dariusz.twirplyapp.presentation.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String) {

    sealed class AppScreens(
        route: String,
        title: String,
        val icon: ImageVector?
    ) : Screens(
        route,
        title
    ) {
        object FeedScreen : AppScreens("feed", "Feed", Icons.Default.Home)
        object SearchScreen : AppScreens("search", "Search", Icons.Default.Search)
        object SearchResults : AppScreens("searchresults", "Search Results", null)
        object ProfileScreen : AppScreens("profile", "Profile", Icons.Default.Person)
        object FollowersScreen : AppScreens("followers", "Followers", null)
        object FollowingScreen : AppScreens("following", "Following", null)
        object TweetScreen : AppScreens("tweet", "Tweet", null)
    }
}

val screensListForBottomNavigation = listOf(
    Screens.AppScreens.FeedScreen,
    Screens.AppScreens.SearchScreen,
    Screens.AppScreens.ProfileScreen
)