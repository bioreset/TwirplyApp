package com.dariusz.twirplyapp.presentation.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.presentation.screens.blocked.BlockedUsersScreen
import com.dariusz.twirplyapp.presentation.screens.feed.FeedScreen
import com.dariusz.twirplyapp.presentation.screens.followers.FollowersScreen
import com.dariusz.twirplyapp.presentation.screens.following.FollowingScreen
import com.dariusz.twirplyapp.presentation.screens.likes.WhoLikedTweetScreen
import com.dariusz.twirplyapp.presentation.screens.profile.ProfileScreen
import com.dariusz.twirplyapp.presentation.screens.retweeted.WhoRetweetedScreen
import com.dariusz.twirplyapp.presentation.screens.retweets.RetweetsScreen
import com.dariusz.twirplyapp.presentation.screens.search.SearchResults
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreen
import com.dariusz.twirplyapp.presentation.screens.splash.SplashScreen
import com.dariusz.twirplyapp.presentation.screens.splash.WebScreen
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun MainNavigationHost(
    navController: NavController,
    tokenRemembered: String
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.FeedScreen.route
    ) {
        composable(route = Screens.AppScreens.FeedScreen.route) {
            FeedScreen(navController = navController)
        }
        composable(route = Screens.AppScreens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screens.AppScreens.SearchResults.route.plus("/{search_query}")) {
            SearchResults(
                query = it.arguments?.getString("search_query") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.ProfileScreen.route.plus("/{user_id}")) {
            ProfileScreen(
                profileID = it.arguments?.getString("user_id") ?: "2244994945",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.FollowersScreen.route.plus("/{user_id}")) {
            FollowersScreen(
                profileID = it.arguments?.getString("user_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.FollowingScreen.route.plus("/{user_id}")) {
            FollowingScreen(
                profileID = it.arguments?.getString("user_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.TweetScreen.route.plus("/{tweet_id}")) {
            TweetScreen(
                tweetID = it.arguments?.getString("tweet_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.BlockedUsersScreen.route.plus("/{user_id}")) {
            BlockedUsersScreen(
                profileID = it.arguments?.getString("user_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.WhoRetweetedScreen.route.plus("/{tweet_id}")) {
            WhoRetweetedScreen(
                tweetID = it.arguments?.getString("tweet_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.WhoLikedTweet.route.plus("/{tweet_id}")) {
            WhoLikedTweetScreen(
                tweetID = it.arguments?.getString("tweet_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.RetweetsScreen.route.plus("/{tweet_id}")) {
            RetweetsScreen(
                tweetID = it.arguments?.getString("tweet_id") ?: "",
                navController = navController,
                token = tokenRemembered
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun SecondaryNavHost(
    navController: NavController
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.SplashScreen.route
    ) {
        composable(route = Screens.AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.AppScreens.WebScreen.route.plus("/{url}")) {
            WebScreen(it.arguments?.getString("url") ?: "", navController)
        }
    }
}