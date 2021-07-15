package com.dariusz.twirplyapp.di

import com.dariusz.twirplyapp.di.NetworkModule.provideTwirplyAppApiAuthService
import com.dariusz.twirplyapp.di.NetworkModule.provideTwirplyAppApiSearchService
import com.dariusz.twirplyapp.di.NetworkModule.provideTwirplyAppApiTweetService
import com.dariusz.twirplyapp.di.NetworkModule.provideTwirplyAppApiUserService
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepository
import com.dariusz.twirplyapp.domain.repository.auth.AuthRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.search.SearchRepository
import com.dariusz.twirplyapp.domain.repository.search.SearchRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepositoryImpl
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import com.dariusz.twirplyapp.domain.repository.user.UserRepositoryImpl
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
        provideTwirplyAppApiAuthService()
    )

    @Provides
    fun provideSearchRepository(
    ): SearchRepository = SearchRepositoryImpl(
        provideTwirplyAppApiSearchService()
    )

    @Provides
    fun provideTweetRepository(
    ): TweetRepository = TweetRepositoryImpl(
        provideTwirplyAppApiTweetService()
    )

    @Provides
    fun provideUserRepository(
    ): UserRepository = UserRepositoryImpl(
        provideTwirplyAppApiUserService()
    )

}
