package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.FollowingList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun FollowingScreen(
    profileID: Long,
    profileScreenViewModel: ProfileScreenViewModel = viewModel(),
    navController: NavController
) {

    val profileIDToDisplayFollowing = remember { mutableStateOf(profileID) }

    val followingToDisplay by remember(profileScreenViewModel) {
        profileScreenViewModel.userFollowing
    }.collectAsState()

    ManageFollowingScreen(followingToDisplay) {
        navigateToWithArgument(
            navController,
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        profileScreenViewModel.getUserFollowing(profileIDToDisplayFollowing.value.toInt())
    }
}

@Composable
fun ManageFollowingScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>>,
    action: (String) -> Unit
) {
    when (input) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            input.data.outputOne?.let {
                FollowingList(input = it) { userID ->
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