package com.dariusz.twirplyapp.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

object ViewModelUtils {

    @Suppress("UNCHECKED_CAST")
    @Composable
    inline fun <reified VM : ViewModel> composeViewModel(crossinline viewModel: () -> VM): VM =
        viewModel(factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModel.invoke() as T
            }
        })


    fun <A, B, C, D> manageResult(
        mutableInput: MutableStateFlow<ResponseState<GenericResponse<A, B, C, D>>>,
        dataFromAction: GenericResponse<A, B, C, D>
    ) = mutableInput.getResultOfGenericResponse(dataFromAction)

    private fun <A, B, C, D> MutableStateFlow<ResponseState<GenericResponse<A, B, C, D>>>.getResultOfGenericResponse(
        data: GenericResponse<A, B, C, D>
    ) {
        value = ResponseState.Idle
        value = try {
            ResponseState.Success(data)
        } catch (exception: Exception) {
            ResponseState.Error(exception)
        }
    }

    private val ViewModel.ioTask
        get() = viewModelScope + Dispatchers.IO

    fun ViewModel.launchVMTask(
        action: suspend () -> Unit
    ) = ioTask.launch {
        action.invoke()
    }

}