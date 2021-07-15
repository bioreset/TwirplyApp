package com.dariusz.twirplyapp.data.remote.api.auth

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitAuth
import com.dariusz.twirplyapp.domain.model.AuthResponse
import com.dariusz.twirplyapp.utils.AuthUtils.prepareBasicClientCredentials
import javax.inject.Inject

interface TwirplyAppApiAuthService {

    suspend fun getNewTempBearerToken(grantType: String): AuthResponse

    suspend fun invalidateTempBearerToken(body: String): AuthResponse

}

class TwirplyAppApiAuthServiceImpl
@Inject constructor(
) : TwirplyAppApiAuthService {

    private fun getRetrofitAuth() = provideRetrofitAuth(prepareBasicClientCredentials())

    override suspend fun getNewTempBearerToken(grantType: String) =
        getRetrofitAuth().getBearerToken(grantType)

    override suspend fun invalidateTempBearerToken(body: String) =
        getRetrofitAuth().invalidateBearerToken(body)

}