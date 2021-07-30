package com.dariusz.twirplyapp.data.remote.api.auth

import com.dariusz.twirplyapp.domain.model.AuthResponse
import com.dariusz.twirplyapp.domain.model.AuthResponseInitial
import retrofit2.http.*

interface TwirplyAppApiAuth {

    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
    @POST("oauth2/token")
    suspend fun getBearerToken(
        @Query("grant_type") grantType: String
    ): AuthResponse

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth2/invalidate_token")
    suspend fun invalidateBearerToken(
        @Body bearerTokenToInvalidate: String
    ): AuthResponse

    @POST("oauth/request_token")
    suspend fun requestOAuthToken(): AuthResponseInitial

    @GET("oauth/authorize")
    suspend fun authorizeUser(): String

    @POST("oauth/access_token")
    suspend fun fetchAccessToken(
        @Query("oauth_token") oauthToken: String,
        @Query("oauth_verifier") oauthVerifier: String
    ): AuthResponseInitial


}