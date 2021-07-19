package com.dariusz.twirplyapp.di

import android.annotation.SuppressLint
import com.dariusz.twirplyapp.data.remote.api.auth.TwirplyAppApiAuth
import com.dariusz.twirplyapp.data.remote.api.auth.TwirplyAppApiAuthService
import com.dariusz.twirplyapp.data.remote.api.auth.TwirplyAppApiAuthServiceImpl
import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearch
import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchService
import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearchServiceImpl
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweet
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetService
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetServiceImpl
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUser
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserService
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserServiceImpl
import com.dariusz.twirplyapp.utils.AuthUtils.generateRandomString
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_ACCESS_TOKEN
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_CONSUMER_KEY
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
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.Instant.now

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @SuppressLint("NewApi")
    @Provides
    fun provideRetrofitService(
        userContext: Boolean = false,
        basic: Boolean = false,
        bearer: Boolean = false,
        authToken: String = "",
        useNetworkInterceptor: Boolean = true
    ): Retrofit {
        val logging: Interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.HEADERS
        }
        val moshi =
            Moshi.Builder().add(KotlinJsonAdapterFactory())
                .build()
        val client = when {
            userContext -> {
                OkHttpClient.Builder().apply {
                    addInterceptor(
                        Interceptor { chain ->
                            val builder = chain.request().newBuilder()
                            builder.header(
                                "Authorization",
                                "OAuth oauth_consumer_key=\"$API_AUTH_CONSUMER_KEY\", " +
                                        "oauth_nonce=\"${generateRandomString()}\", oauth_signature=\"OAUTH_SIGNATURE\", " +
                                        "oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"${now()}\", " +
                                        "oauth_token=\"${API_AUTH_ACCESS_TOKEN}\", " +
                                        "oauth_version=\"1.0\""
                            )
                            return@Interceptor chain.proceed(builder.build())
                        }
                    )
                }.addNetworkInterceptor(logging).build()
            }
            bearer -> {
                OkHttpClient.Builder().apply {
                    addInterceptor(
                        Interceptor { chain ->
                            val builder = chain.request().newBuilder()
                            builder.header("Authorization", "Bearer $authToken")
                            return@Interceptor chain.proceed(builder.build())
                        }
                    )
                }.addNetworkInterceptor(logging).build()
            }
            basic -> {
                OkHttpClient.Builder().apply {
                    addInterceptor(
                        Interceptor { chain ->
                            val builder = chain.request().newBuilder()
                            builder.header("Authorization", "Basic $authToken")
                            return@Interceptor chain.proceed(builder.build())
                        }
                    )
                }.addNetworkInterceptor(logging).build()
            }
            useNetworkInterceptor -> {
                OkHttpClient.Builder().addNetworkInterceptor(logging).build()
            }
            else -> {
                OkHttpClient.Builder().build()
            }
        }
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideRetrofitSearch(token: String): TwirplyAppApiSearch =
        provideRetrofitService(
            bearer = true,
            authToken = token
        ).create(TwirplyAppApiSearch::class.java)

    @Provides
    fun provideRetrofitTweet(token: String): TwirplyAppApiTweet =
        provideRetrofitService(
            bearer = true,
            authToken = token
        ).create(TwirplyAppApiTweet::class.java)

    @Provides
    fun provideRetrofitUser(token: String): TwirplyAppApiUser =
        provideRetrofitService(
            bearer = true,
            authToken = token
        ).create(TwirplyAppApiUser::class.java)

    @Provides
    fun provideRetrofitAuth(token: String): TwirplyAppApiAuth =
        provideRetrofitService(
            basic = true,
            authToken = token
        ).create(TwirplyAppApiAuth::class.java)


    @Provides
    fun provideTwirplyAppApiTweetService(): TwirplyAppApiTweetService =
        TwirplyAppApiTweetServiceImpl()

    @Provides
    fun provideTwirplyAppApiUserService(): TwirplyAppApiUserService =
        TwirplyAppApiUserServiceImpl()

    @Provides
    fun provideTwirplyAppApiSearchService(): TwirplyAppApiSearchService =
        TwirplyAppApiSearchServiceImpl()

    @Provides
    fun provideTwirplyAppApiAuthService(): TwirplyAppApiAuthService =
        TwirplyAppApiAuthServiceImpl()

}