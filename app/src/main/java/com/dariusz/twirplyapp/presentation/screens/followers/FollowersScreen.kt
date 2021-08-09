package com.dariusz.twirplyapp.presentation.screens.followers

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowersList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun FollowersScreen(
    profileID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = FollowersScreenViewModel(provideUserRepository()),
        inputFromVM = { viewModel ->
            viewModel.userFollowers
        },
        launchEffect = { viewModel ->
            viewModel.getUserFollowers(profileID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
                response.outputOne?.let { list ->
                    FollowersList(list) { userID ->
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
