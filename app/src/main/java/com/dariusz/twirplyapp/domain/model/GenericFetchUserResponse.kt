package com.dariusz.twirplyapp.domain.model

data class GenericFetchUserResponse(
    val data: User? = null,
    val includes: Includes? = null,
    val errors: Errors? = null
)
