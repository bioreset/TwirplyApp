package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowersList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen

@ExperimentalCoilApi
@Composable
fun FollowersScreen(
    profileID: String,
    profileScreenViewModel: ProfileScreenViewModel,
    navController: NavController,
    token: String
) {

    val followersToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFollowers
    }.collectAsState()

    ManageFollowersScreen(followersToDisplay) {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        profileScreenViewModel.getUserFollowers(profileID, token)
    }

}

@ExperimentalCoilApi
@Composable
fun ManageFollowersScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>,
    action: (String) -> Unit
) {
    ManageResponseOnScreen(input = input) { response ->
        response.outputOne?.let { list ->
            FollowersList(input = list) { userID ->
                action.invoke(userID)
            }
        }
    }

}
