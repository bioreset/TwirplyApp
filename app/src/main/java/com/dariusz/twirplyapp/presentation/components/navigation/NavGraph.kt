package com.dariusz.twirplyapp.presentation.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideSearchRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideTweetRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.screens.feed.FeedScreen
import com.dariusz.twirplyapp.presentation.screens.profile.*
import com.dariusz.twirplyapp.presentation.screens.search.SearchResults
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreen
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreenViewModelFactory
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreen
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreenViewModelFactory
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun MainNavigationHost(
    navController: NavController,
    idOfLoggedInUser: Long = 0,
    mainViewModel: MainViewModel = viewModel()
) {
    val searchScreenViewModel: SearchScreenViewModel = viewModel(
        factory = SearchScreenViewModelFactory(
            provideSearchRepository()
        )
    )
    val tweetScreenViewModel: TweetScreenViewModel = viewModel(
        factory = TweetScreenViewModelFactory(
            provideTweetRepository()
        )
    )
    val profileScreenViewModel: ProfileScreenViewModel = viewModel(
        factory = ProfileScreenViewModelFactory(
            provideUserRepository(),
            provideTweetRepository()
        )
    )
    val tokenRemembered by remember(mainViewModel) {
        mainViewModel.bearerToken
    }.collectAsState()

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.FeedScreen.route
    ) {
        composable(route = Screens.AppScreens.FeedScreen.route) {
            FeedScreen(idOfLoggedInUser.toString(), navController)
        }
        composable(route = Screens.AppScreens.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(route = Screens.AppScreens.SearchResults.route.plus("/{search_query}")) {
            SearchResults(
                it.arguments?.getString("search_query") ?: "",
                searchScreenViewModel,
                navController,
                tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.ProfileScreen.route.plus("/{user_id}")) {
            ProfileScreen(
                it.arguments?.getString("user_id") ?: "",
                profileScreenViewModel,
                navController,
                tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.FollowersScreen.route.plus("/{user_id}")) {
            FollowersScreen(
                it.arguments?.getString("user_id") ?: "",
                profileScreenViewModel,
                navController,
                tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.FollowingScreen.route.plus("/{user_id}")) {
            FollowingScreen(
                it.arguments?.getString("user_id") ?: "",
                profileScreenViewModel,
                navController,
                tokenRemembered
            )
        }
        composable(route = Screens.AppScreens.TweetScreen.route.plus("/{tweet_id}")) {
            TweetScreen(
                it.arguments?.getString("tweet_id") ?: "",
                tweetScreenViewModel,
                navController,
                tokenRemembered
            )
        }
    }
}