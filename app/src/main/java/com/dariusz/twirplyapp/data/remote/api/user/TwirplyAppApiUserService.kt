package com.dariusz.twirplyapp.data.remote.api.user

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofitUser
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiUserService {

    suspend fun getAllUserDataBasedOnId(id: Int): GenericResponse<User?, Includes?, Errors?, Nothing>

    suspend fun getCompactUserDataBasedOnId(id: Int): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

}

class TwirplyAppApiUserServiceImpl : TwirplyAppApiUserService {

    private val retrofit = provideRetrofitUser()

    override suspend fun getAllUserDataBasedOnId(id: Int) = retrofit.fetchAllUserDataBasedOnId(id)

    override suspend fun getCompactUserDataBasedOnId(id: Int) =
        retrofit.fetchCompactUserDataBasedOnId(id)

}