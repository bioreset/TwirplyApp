package com.dariusz.twirplyapp.domain.repository.auth

import com.dariusz.twirplyapp.data.remote.api.auth.TwirplyAppApiAuthService
import com.dariusz.twirplyapp.domain.model.AuthResponse
import javax.inject.Inject

interface AuthRepository {

    suspend fun fetchBearerTokenAndSaveIt(): AuthResponse

    suspend fun invalidateCurrentBearerToken(token: String): AuthResponse

}

class AuthRepositoryImpl
@Inject constructor(
    private val authService: TwirplyAppApiAuthService
) : AuthRepository {

    override suspend fun fetchBearerTokenAndSaveIt() =
        authService.getNewTempBearerToken("client_credentials")

    override suspend fun invalidateCurrentBearerToken(token: String) =
        authService.invalidateTempBearerToken("access_token=$token")


}