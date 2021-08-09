package com.dariusz.twirplyapp.presentation.screens.following

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowingList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun FollowingScreen(
    profileID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = FollowingScreenViewModel(provideUserRepository()),
        inputFromVM = { viewModel ->
            viewModel.userFollowing
        },
        launchEffect = { viewModel ->
            viewModel.getUserFollowing(profileID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
                response.outputOne?.let { list ->
                    FollowingList(list) { userID ->
                        navController.navigateToWithArgument(
                            Screens.AppScreens.ProfileScreen.route,
                            userID
                        )
                    }
                }
            }
        }
    )
}
