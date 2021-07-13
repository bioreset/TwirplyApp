package com.dariusz.twirplyapp.presentation.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.twirplyapp.di.RepositoryModule.provideSearchRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideTweetRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.MainViewModelFactory
import com.dariusz.twirplyapp.presentation.screens.feed.FeedScreen
import com.dariusz.twirplyapp.presentation.screens.profile.*
import com.dariusz.twirplyapp.presentation.screens.search.SearchResults
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreen
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreenViewModelFactory
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreen

@ExperimentalComposeUiApi
@Composable
fun MainNavigationHost(navController: NavController) {
    val searchScreenViewModel: SearchScreenViewModel = viewModel(
        factory = SearchScreenViewModelFactory(
            provideSearchRepository()
        )
    )
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            provideTweetRepository()
        )
    )
    val profileScreenViewModel: ProfileScreenViewModel = viewModel(
        factory = ProfileScreenViewModelFactory(
            provideUserRepository()
        )
    )
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.FeedScreen.route
    ) {
        composable(route = Screens.AppScreens.FeedScreen.route) {
            FeedScreen(mainViewModel)
        }
        composable(route = Screens.AppScreens.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(route = Screens.AppScreens.SearchResults.route.plus("/{search_query}")) {
            SearchResults(
                it.arguments?.getString("search_query") ?: "",
                searchScreenViewModel,
                navController
            )
        }
        composable(route = Screens.AppScreens.ProfileScreen.route.plus("/{user_id}")) {
            ProfileScreen(
                it.arguments?.getLong("user_id") ?: 0,
                profileScreenViewModel,
                mainViewModel,
                navController
            )
        }
        composable(route = Screens.AppScreens.FollowersScreen.route.plus("/{user_id}")) {
            FollowersScreen(
                it.arguments?.getLong("user_id") ?: 0,
                profileScreenViewModel,
                navController
            )
        }
        composable(route = Screens.AppScreens.FollowingScreen.route.plus("/{user_id}")) {
            FollowingScreen(
                it.arguments?.getLong("user_id") ?: 0,
                profileScreenViewModel,
                navController
            )
        }
        composable(route = Screens.AppScreens.TweetScreen.route.plus("/{tweet_id}")) {
            TweetScreen(it.arguments?.getLong("tweet_id") ?: 0, mainViewModel, navController)
        }
    }
}