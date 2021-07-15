package com.dariusz.twirplyapp.data.remote.api.auth

import com.dariusz.twirplyapp.domain.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TwirplyAppApiAuth {

    //OAUTH 2.0 BEARER

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

}