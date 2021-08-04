package com.dariusz.twirplyapp.presentation.screens.following

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserRepository
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowingList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel

@ExperimentalCoilApi
@Composable
fun FollowingScreen(
    profileID: String,
    navController: NavController,
    token: String
) {

    val followingScreenViewModel = composeViewModel {
        FollowingScreenViewModel(
            provideUserRepository()
        )
    }

    val followingToDisplay by remember(followingScreenViewModel) {
        followingScreenViewModel.userFollowing
    }.collectAsState()

    ManageFollowingScreen(followingToDisplay) {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        followingScreenViewModel.getUserFollowing(profileID, token)
    }
}

@ExperimentalCoilApi
@Composable
fun ManageFollowingScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>,
    action: (String) -> Unit
) {
    ManageResponseOnScreen(input = input) { response ->
        response.outputOne?.let { list ->
            FollowingList(input = list) { userID ->
                action.invoke(userID)
            }
        }
    }
}