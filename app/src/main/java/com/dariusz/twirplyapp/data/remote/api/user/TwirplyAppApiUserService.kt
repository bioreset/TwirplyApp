package com.dariusz.twirplyapp.data.remote.api.user

import com.dariusz.twirplyapp.di.NetworkModule.provideRetrofit
import com.dariusz.twirplyapp.domain.model.*

interface TwirplyAppApiUserService {

    suspend fun getAllUserDataBasedOnId(id: Int): GenericResponse<User?, Includes?, Errors?, Nothing>

    suspend fun getCompactUserDataBasedOnId(id: Int): GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>

}

class TwirplyAppApiUserServiceImpl : TwirplyAppApiUserService {

    private val retrofit = provideRetrofit(TwirplyAppApiUser::class.java)

    override suspend fun getAllUserDataBasedOnId(id: Int) = retrofit.fetchAllUserDataBasedOnId(id)

    override suspend fun getCompactUserDataBasedOnId(id: Int) =
        retrofit.fetchCompactUserDataBasedOnId(id)

}