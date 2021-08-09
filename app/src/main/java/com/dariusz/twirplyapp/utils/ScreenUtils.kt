package com.dariusz.twirplyapp.utils

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.flow.StateFlow

object ScreenUtils {

    @Composable
    inline fun <reified VM : ViewModel, T> DisplayScreen(
        viewModel: VM,
        inputFromVM: (VM) -> StateFlow<T>,
        crossinline launchEffect: (VM) -> Unit,
        composable: @Composable (T) -> Unit
    ) {

        val coroutineScope = rememberCoroutineScope()

        val initVM = composeViewModel {
            viewModel
        }

        val initInput by remember(initVM) {
            inputFromVM.invoke(initVM)
        }.collectAsState()

        LaunchedEffect(coroutineScope) {
            launchEffect.invoke(initVM)
        }

        composable.invoke(initInput)

    }

}