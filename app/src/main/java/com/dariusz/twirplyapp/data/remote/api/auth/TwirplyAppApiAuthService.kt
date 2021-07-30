package com.dariusz.twirplyapp.data.remote.api.auth

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitAuth
import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitAuthInitial
import com.dariusz.twirplyapp.domain.model.AuthResponse
import com.dariusz.twirplyapp.domain.model.AuthResponseInitial
import com.dariusz.twirplyapp.utils.AuthUtils.prepareBasicClientCredentials
import javax.inject.Inject

interface TwirplyAppApiAuthService {

    suspend fun getNewTempBearerToken(grantType: String): AuthResponse

    suspend fun invalidateTempBearerToken(body: String): AuthResponse

    suspend fun requestOAuthToken(): AuthResponseInitial

    suspend fun authorizeUser(): String

    suspend fun fetchAccessToken(
        oauthToken: String,
        oauthVerifier: String
    ): AuthResponseInitial

}

class TwirplyAppApiAuthServiceImpl
@Inject constructor(
) : TwirplyAppApiAuthService {

    private fun getRetrofitAuth() = provideRetrofitAuth(prepareBasicClientCredentials())

    private fun getRetrofitAuthInitial() = provideRetrofitAuthInitial()

    override suspend fun getNewTempBearerToken(grantType: String) =
        getRetrofitAuth().getBearerToken(grantType)

    override suspend fun invalidateTempBearerToken(body: String) =
        getRetrofitAuth().invalidateBearerToken(body)

    override suspend fun requestOAuthToken() = getRetrofitAuthInitial().requestOAuthToken()

    override suspend fun authorizeUser() = getRetrofitAuthInitial().authorizeUser()

    override suspend fun fetchAccessToken(
        oauthToken: String,
        oauthVerifier: String
    ) = getRetrofitAuthInitial().fetchAccessToken(oauthToken, oauthVerifier)
}