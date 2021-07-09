package com.dariusz.twirplyapp.domain.model

sealed class ResponseState<out T>{
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val exception: Exception) : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()
    object Idle : ResponseState<Nothing>()
}