package com.dariusz.twirplyapp.presentation.screens.blocked

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserContextRepository
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.profile.BlockedUsersList
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ResponseUtils.ManageResponseOnScreen
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel

@ExperimentalCoilApi
@Composable
fun BlockedUsersScreen(
    profileID: String,
    navController: NavController,
    token: String
) {

    val blockedUsersScreenViewModel = composeViewModel {
        BlockedUsersScreenViewModel(
            provideUserContextRepository()
        )
    }

    val blockedUsersToDisplay by remember(blockedUsersScreenViewModel) {
        blockedUsersScreenViewModel.blockedUsers
    }.collectAsState()

    ManageBlockedUsersScreen(blockedUsersToDisplay) {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    }

    LaunchedEffect(Unit) {
        blockedUsersScreenViewModel.getBlockedUsers(profileID, token)
    }

}

@ExperimentalCoilApi
@Composable
fun ManageBlockedUsersScreen(
    input: ResponseState<GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>>,
    action: (String) -> Unit
) {
    ManageResponseOnScreen(input = input) { response ->
        response.outputOne?.let { list ->
            BlockedUsersList(input = list) { userID ->
                action.invoke(userID)
            }
        }
    }

}