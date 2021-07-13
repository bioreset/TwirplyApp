package com.dariusz.twirplyapp.domain.repository.user

import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface UserRepository {

    suspend fun returnAllUserInfo(userID: Int): GenericResponse<User?, Includes?, Errors?, Nothing>

    suspend fun getAllUserDataBasedOnUsername(name: String): GenericResponse<User?, Includes?, Errors?, Nothing>

    suspend fun getCompactUserDataBasedOnUsername(name: String): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

    suspend fun fetchUserFollowersBasedOnId(userID: Int): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>

    suspend fun fetchUserFollowingBasedOnId(userID: Int): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>

}

class UserRepositoryImpl
@Inject constructor(
    private val apiUserService: TwirplyAppApiUserService
) : UserRepository {

    override suspend fun returnAllUserInfo(userID: Int) =
        apiUserService.getAllUserDataBasedOnId(userID)

    override suspend fun getAllUserDataBasedOnUsername(name: String) =
        apiUserService.getAllUserDataBasedOnUsername(name)

    override suspend fun getCompactUserDataBasedOnUsername(name: String) =
        apiUserService.getCompactUserDataBasedOnUsername(name)

    override suspend fun fetchUserFollowersBasedOnId(userID: Int) =
        apiUserService.fetchUserFollowersBasedOnId(userID)

    override suspend fun fetchUserFollowingBasedOnId(userID: Int) =
        apiUserService.fetchUserFollowingBasedOnId(userID)

}

