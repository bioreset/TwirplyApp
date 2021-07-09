package com.dariusz.twirplyapp.presentation.components.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.twirplyapp.di.RepositoryModule.provideSearchRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideTweetRepository
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.presentation.screens.feed.FeedScreen
import com.dariusz.twirplyapp.presentation.screens.feed.FeedScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.feed.FeedScreenViewModelFactory
import com.dariusz.twirplyapp.presentation.screens.profile.ProfileScreen
import com.dariusz.twirplyapp.presentation.screens.profile.ProfileScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.profile.ProfileScreenViewModelFactory
import com.dariusz.twirplyapp.presentation.screens.search.SearchResults
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreen
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.search.SearchScreenViewModelFactory
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreen
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreenViewModel
import com.dariusz.twirplyapp.presentation.screens.tweet.TweetScreenViewModelFactory
import com.dariusz.twirplyapp.utils.Constants.PROFILE_ID_FOR_TESTS
import com.dariusz.twirplyapp.utils.Constants.QUERY_FOR_TESTS
import com.dariusz.twirplyapp.utils.Constants.TWEET_ID_FOR_TESTS

@Composable
fun MainNavigationHost(navController: NavController) {
    val feedScreenViewModel: FeedScreenViewModel = viewModel(
        factory = FeedScreenViewModelFactory(
            provideTweetRepository()
        )
    )
    val searchScreenViewModel: SearchScreenViewModel = viewModel(
        factory = SearchScreenViewModelFactory(
            provideSearchRepository()
        )
    )
    val profileScreenViewModel: ProfileScreenViewModel = viewModel(
        factory = ProfileScreenViewModelFactory(
            provideUserRepository()
        )
    )
    val tweetScreenViewModel: TweetScreenViewModel = viewModel(
        factory = TweetScreenViewModelFactory(
            provideTweetRepository()
        )
    )
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.FeedScreen.route
    ) {
        composable(route = Screens.AppScreens.FeedScreen.route) {
            FeedScreen(feedScreenViewModel)
        }
        composable(route = Screens.AppScreens.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(route = Screens.AppScreens.SearchResults.route) {
            SearchResults(QUERY_FOR_TESTS, searchScreenViewModel)
        }
        composable(route = Screens.AppScreens.ProfileScreen.route) {
            ProfileScreen(PROFILE_ID_FOR_TESTS, profileScreenViewModel)
        }
        composable(route = Screens.AppScreens.TweetScreen.route) {
            TweetScreen(TWEET_ID_FOR_TESTS, tweetScreenViewModel)
        }
    }
}