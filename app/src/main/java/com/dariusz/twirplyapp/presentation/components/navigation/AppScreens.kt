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
        //main navhost
        object FeedScreen : AppScreens("feed", "Feed", Icons.Default.Home)
        object SearchScreen : AppScreens("search", "Search", Icons.Default.Search)
        object SearchResults : AppScreens("searchresults", "Search Results", null)
        object ProfileScreen : AppScreens("profile", "Profile", Icons.Default.Person)
        object FollowersScreen : AppScreens("followers", "Followers", null)
        object FollowingScreen : AppScreens("following", "Following", null)
        object TweetScreen : AppScreens("tweet", "Tweet", null)
        object BlockedUsersScreen : AppScreens("blockedusers", "Blocked users", null)
        object WhoRetweetedScreen : AppScreens("whoretweeted", "Who retweeted", null)
        object WhoLikedTweet : AppScreens("wholiked", "Likes", null)
        object RetweetsScreen : AppScreens("retweets", "Retweets", null)

        //secondary navhost
        object SplashScreen : AppScreens("splash", "Splash", null)
        object WebScreen : AppScreens("web", "Web", null)
    }
}

val screensListForBottomNavigation = listOf(
    Screens.AppScreens.FeedScreen,
    Screens.AppScreens.SearchScreen,
    Screens.AppScreens.ProfileScreen
)