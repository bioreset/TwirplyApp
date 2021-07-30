package com.dariusz.twirplyapp.di

import com.dariusz.twirplyapp.data.remote.api.auth.TwirplyAppApiAuthServiceImpl
import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchServiceImpl
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetServiceImpl
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserServiceImpl
import com.dariusz.twirplyapp.data.remote.api.useractions.TwirplyAppApiUserActionsServiceImpl
import com.dariusz.twirplyapp.data.remote.api.usercontext.TwirplyAppApiUserContextServiceImpl
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepository
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.search.SearchRepository
import com.dariusz.twirplyapp.domain.repository.search.SearchRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import com.dariusz.twirplyapp.domain.repository.user.UserRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.useractions.UserActionsRepository
import com.dariusz.twirplyapp.domain.repository.useractions.UserActionsRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.usercontext.UserContextRepository
import com.dariusz.twirplyapp.domain.repository.usercontext.UserContextRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
    ): AuthRepository = AuthRepositoryImpl(
        TwirplyAppApiAuthServiceImpl()
    )

    @Provides
    fun provideSearchRepository(
    ): SearchRepository = SearchRepositoryImpl(
        TwirplyAppApiSearchServiceImpl()
    )

    @Provides
    fun provideTweetRepository(
    ): TweetRepository = TweetRepositoryImpl(
        TwirplyAppApiTweetServiceImpl()
    )

    @Provides
    fun provideUserRepository(
    ): UserRepository = UserRepositoryImpl(
        TwirplyAppApiUserServiceImpl()
    )

    @Provides
    fun provideUserActionRepository(): UserActionsRepository = UserActionsRepositoryImpl(
        TwirplyAppApiUserActionsServiceImpl()
    )

    @Provides
    fun provideUserContextRepository(): UserContextRepository = UserContextRepositoryImpl(
        TwirplyAppApiUserContextServiceImpl()
    )

}
