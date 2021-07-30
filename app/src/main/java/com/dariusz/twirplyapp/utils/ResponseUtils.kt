package com.dariusz.twirplyapp.utils

import androidx.compose.runtime.Composable
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.ResponseState
import com.dariusz.twirplyapp.presentation.components.common.CenteredText
import com.dariusz.twirplyapp.presentation.components.common.LoadingComponent

object ResponseUtils {

    @Composable
    fun <A, B, C, D> ManageResponseOnScreen(
        input: ResponseState<GenericResponse<A, B, C, D>>,
        content: @Composable (GenericResponse<A, B, C, D>) -> Unit,
    ) {
        when (input) {
            is ResponseState.Loading -> {
                LoadingComponent()
            }
            is ResponseState.Success -> {
                content.invoke(input.data)
            }
            is ResponseState.Error -> {
                CenteredText("Error: ${input.exception}")
            }
            is ResponseState.Idle -> {
                //default option; do nothing
            }
        }
    }


}