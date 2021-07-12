package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.MainViewModel
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent
import com.dariusz.twirplyapp.presentation.components.profile.ProfileFeed

@Composable
fun ProfileScreen(
    profileID: Long,
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController
) {

    val profileIDToDisplay = remember { mutableStateOf(profileID) }

    val profileToDisplay by remember(mainViewModel) {
        mainViewModel.userFullData
    }.collectAsState()

    ManageProfileScreen(profileToDisplay, navController)

    LaunchedEffect(Unit) {
        mainViewModel.getUserFullData(profileIDToDisplay.value.toInt())
    }

}

@Composable
fun ManageProfileScreen(
    profile: ResponseState<GenericResponse<User?, Includes?, Errors?, Nothing>>,
    navController: NavController
) {
    when (profile) {
        is ResponseState.Loading -> {
            LoadingComponent()
        }
        is ResponseState.Success -> {
            ProfileFeed(user = profile.data, navController)
        }
        is ResponseState.Error -> {
            Text("Profile Error")
        }
        else -> {
            Text("Profile Here")
        }
    }
}