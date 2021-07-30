package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowingList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen

@ExperimentalCoilApi
@Composable
fun FollowingScreen(
    profileID: String,
    profileScreenViewModel: ProfileScreenViewModel,
    navController: NavController,
    token: String
) {

    val followingToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFollowing
    }.collectAsState()

    ManageFollowingScreen(followingToDisplay) {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        profileScreenViewModel.getUserFollowing(profileID, token)
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