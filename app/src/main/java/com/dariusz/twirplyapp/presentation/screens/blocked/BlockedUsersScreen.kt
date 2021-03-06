package com.dariusz.twirplyapp.presentation.screens.blocked

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.BlockedUsersList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ScreenUtils.DisplayScreen

@ExperimentalCoilApi
@Composable
fun BlockedUsersScreen(
    profileID: String,
    navController: NavController,
    token: String
) {
    DisplayScreen(
        viewModel = BlockedUsersScreenViewModel(provideUserContextRepository()),
        inputFromVM = { viewModel ->
            viewModel.blockedUsers
        },
        launchEffect = { viewModel ->
            viewModel.getBlockedUsers(profileID, token)
        },
        composable = { responseState ->
            ManageResponseOnScreen(responseState) { response ->
                response.outputOne?.let { list ->
                    BlockedUsersList(list) { userID ->
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