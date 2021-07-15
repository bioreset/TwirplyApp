package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @field:Json(name = "token_type")
    val tokenType: String?,
    @field:Json(name = "access_token")
    val accessToken: String
)

