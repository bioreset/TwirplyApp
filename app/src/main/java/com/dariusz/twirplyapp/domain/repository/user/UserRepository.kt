package com.dariusz.twirplyapp.domain.repository.user

import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserService
import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.User
import javax.inject.Inject

interface UserRepository {

    suspend fun returnAllUserInfo(userID: Int): GenericResponse<User?, Includes?, Errors?, Nothing>

}

class UserRepositoryImpl
@Inject constructor(
    private val apiUserService: TwirplyAppApiUserService
) : UserRepository {

    override suspend fun returnAllUserInfo(userID: Int) =
        apiUserService.getAllUserDataBasedOnId(userID)

}

