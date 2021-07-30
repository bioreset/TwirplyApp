package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @field:Json(name = "token_type")
    val tokenType: String?,
    @field:Json(name = "access_token")
    val accessToken: String?
)

@JsonClass(generateAdapter = true)
data class AuthResponseInitial(
    @field:Json(name = "oauth_token")
    val oauthToken: String? = "",
    @field:Json(name = "oauth_token_secret")
    val oauthSecretToken: String? = "",
    @field:Json(name = "user_id")
    val userID: Long? = 0,
    @field:Json(name = "twitterapi")
    val screenName: String? = "",
    @field:Json(name = "oauth_callback_confirmed")
    val oauthCallbackConfirmed: Boolean? = false
)
