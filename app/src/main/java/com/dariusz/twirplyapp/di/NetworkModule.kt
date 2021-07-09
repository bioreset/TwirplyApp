package com.dariusz.twirplyapp.di

import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchService
import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchServiceImpl
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetService
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetServiceImpl
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserService
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserServiceImpl
import com.dariusz.twirplyapp.utils.Constants.API_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun <T> provideRetrofit(
        apiInterface: Class<T>,
        tokenLess: Boolean = false,
        useNetworkInterceptor: Boolean = true
    ): T {
        val token = " //TODO BEARER TOKEN"
        val logging: Interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.HEADERS
        }
        val moshi =
            Moshi.Builder().add(KotlinJsonAdapterFactory())
                .build()
        val client = if (!tokenLess) {
            OkHttpClient.Builder().apply {
                addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("Authorization", "Bearer $token")
                        return@Interceptor chain.proceed(builder.build())
                    }
                )
            }.addNetworkInterceptor(logging).build()
        } else if (useNetworkInterceptor) {
            OkHttpClient.Builder().addNetworkInterceptor(logging).build()
        } else {
            null
        }
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client!!)
            .build()
            .create(apiInterface)
    }

    @Provides
    fun provideTwirplyAppApiTweetService(): TwirplyAppApiTweetService =
        TwirplyAppApiTweetServiceImpl()

    @Provides
    fun provideTwirplyAppApiUserService(): TwirplyAppApiUserService = TwirplyAppApiUserServiceImpl()

    @Provides
    fun provideTwirplyAppApiSearchService(): TwirplyAppApiSearchService =
        TwirplyAppApiSearchServiceImpl()

}