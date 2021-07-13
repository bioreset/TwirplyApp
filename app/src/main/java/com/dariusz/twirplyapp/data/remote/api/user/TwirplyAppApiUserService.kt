package com.dariusz.twirplyapp.data.remote.api.user

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitUser
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiUserService {

    suspend fun getAllUserDataBasedOnId(id: Int): GenericResponse<User?, Includes?, Errors?, Nothing>

    suspend fun getCompactUserDataBasedOnId(id: Int): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

    suspend fun getAllUserDataBasedOnUsername(name: String): GenericResponse<User?, Includes?, Errors?, Nothing>

    suspend fun getCompactUserDataBasedOnUsername(name: String): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

    suspend fun fetchUserFollowersBasedOnId(userID: Int): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>

    suspend fun fetchUserFollowingBasedOnId(userID: Int): GenericResponse<List<UserMinimum>?, Includes?, Errors?, Nothing>

}

class TwirplyAppApiUserServiceImpl : TwirplyAppApiUserService {

    private val retrofit = provideRetrofitUser()

    override suspend fun getAllUserDataBasedOnId(id: Int) = retrofit.fetchAllUserDataBasedOnId(id)

    override suspend fun getCompactUserDataBasedOnId(id: Int) =
        retrofit.fetchCompactUserDataBasedOnId(id)

    override suspend fun getAllUserDataBasedOnUsername(name: String) =
        retrofit.fetchAllUserDataBasedOnUsername(name)

    override suspend fun getCompactUserDataBasedOnUsername(name: String) =
        retrofit.fetchCompactUserDataBasedOnUsername(name)

    override suspend fun fetchUserFollowersBasedOnId(userID: Int) =
        retrofit.fetchUserFollowersBasedOnId(userID)

    override suspend fun fetchUserFollowingBasedOnId(userID: Int) =
        retrofit.fetchUserFollowingBasedOnId(userID)

}