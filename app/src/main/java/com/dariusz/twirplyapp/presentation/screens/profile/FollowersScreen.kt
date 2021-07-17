package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowersList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun FollowersScreen(
    profileID: String,
    profileScreenViewModel: ProfileScreenViewModel = viewModel(),
    navController: NavController,
    token: String
) {

    val followersToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFollowers
    }.collectAsState()

    ManageFollowersScreen(input = followersToDisplay) {
        navigateToWithArgument(
            navController,
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        profileScreenViewModel.getUserFollowers(profileID, token)
    }

}

@Composable
fun ManageFollowersScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>,
    action: (String) -> Unit
) {
    when (input) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            input.data.outputOne?.let {
                FollowersList(input = it) { userID ->
                    action.invoke(userID)
                }
            }
        }
        is ResponseState.Error -> {
            Text("Followers Error")
        }
        else -> {
            Text("Followers Here")
        }
    }
}