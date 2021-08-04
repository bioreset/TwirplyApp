package com.dariusz.twirplyapp.utils

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.flow.StateFlow

object ScreenUtils {

    //TESTING

    @Composable
    inline fun <reified VM : ViewModel, T> InitScreen(
        navController: NavController,
        viewModel: VM,
        inputFromVM: (VM) -> StateFlow<T>,
        crossinline launchEffect: (VM) -> Unit,
        composable: @Composable (T, NavController) -> Unit
    ) {

        val initVM = composeViewModel {
            viewModel
        }

        val initInput by remember(initVM) {
            inputFromVM.invoke(initVM)
        }.collectAsState()

        LaunchedEffect(Unit) {
            launchEffect.invoke(initVM)
        }

        composable.invoke(initInput, navController)

    }

}