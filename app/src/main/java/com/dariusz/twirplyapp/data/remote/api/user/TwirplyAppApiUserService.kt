package com.dariusz.twirplyapp.data.remote.api.user

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitUser
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiUserService {

    suspend fun getAllUserDataBasedOnId(
        id: String,
        token: String
    ): GenericResponse<User?, Includes?, Errors?, Meta?>

    suspend fun getCompactUserDataBasedOnId(
        id: String,
        token: String
    ): GenericResponse<UserMinimum?, Includes?, Errors?, Meta?>

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

class TwirplyAppApiUserServiceImpl : TwirplyAppApiUserService {

    override suspend fun getAllUserDataBasedOnId(id: String, token: String) =
        provideRetrofitUser(token).fetchAllUserDataBasedOnId(id)

    override suspend fun getCompactUserDataBasedOnId(id: String, token: String) =
        provideRetrofitUser(token).fetchCompactUserDataBasedOnId(id)

    override suspend fun getAllUserDataBasedOnUsername(name: String, token: String) =
        provideRetrofitUser(token).fetchAllUserDataBasedOnUsername(name)

    override suspend fun getCompactUserDataBasedOnUsername(name: String, token: String) =
        provideRetrofitUser(token).fetchCompactUserDataBasedOnUsername(name)

    override suspend fun fetchUserFollowersBasedOnId(userID: String, token: String) =
        provideRetrofitUser(token).fetchUserFollowersBasedOnId(userID)

    override suspend fun fetchUserFollowingBasedOnId(userID: String, token: String) =
        provideRetrofitUser(token).fetchUserFollowingBasedOnId(userID)

}