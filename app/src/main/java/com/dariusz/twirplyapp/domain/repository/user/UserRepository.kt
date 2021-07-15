package com.dariusz.twirplyapp.domain.repository.user

import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface UserRepository {

    suspend fun returnAllUserInfo(
        userID: String,
        token: String
    ): GenericResponse<User?, Includes?, Errors?, Meta?>

    suspend fun getAllUserDataBasedOnUsername(
        name: String,
        token: String
    ): GenericResponse<User?, Includes?, Errors?, Meta?>

    suspend fun getCompactUserDataBasedOnUsername(
        name: String,
        token: String
    ): GenericResponse<UserMinimum?, Includes?, Errors?, Meta?>

    suspend fun fetchUserFollowersBasedOnId(
        userID: String,
        token: String
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

    suspend fun fetchUserFollowingBasedOnId(
        userID: String,
        token: String
    ): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Meta?>

}

class UserRepositoryImpl
@Inject constructor(
    private val apiUserService: TwirplyAppApiUserService
) : UserRepository {

    override suspend fun returnAllUserInfo(userID: String, token: String) =
        apiUserService.getAllUserDataBasedOnId(userID, token)

    override suspend fun getAllUserDataBasedOnUsername(name: String, token: String) =
        apiUserService.getAllUserDataBasedOnUsername(name, token)

    override suspend fun getCompactUserDataBasedOnUsername(name: String, token: String) =
        apiUserService.getCompactUserDataBasedOnUsername(name, token)

    override suspend fun fetchUserFollowersBasedOnId(userID: String, token: String) =
        apiUserService.fetchUserFollowersBasedOnId(userID, token)

    override suspend fun fetchUserFollowingBasedOnId(userID: String, token: String) =
        apiUserService.fetchUserFollowingBasedOnId(userID, token)

}

