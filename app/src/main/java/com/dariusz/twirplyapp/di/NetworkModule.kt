package com.dariusz.twirplyapp.di

import android.annotation.SuppressLint
import com.dariusz.twirplyapp.data.remote.api.auth.TwirplyAppApiAuth
import com.dariusz.twirplyapp.data.remote.api.search.TwirplyAppApiSearch
import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweet
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUser
import com.dariusz.twirplyapp.data.remote.api.useractions.TwirplyAppApiUserActions
import com.dariusz.twirplyapp.data.remote.api.usercontext.TwirplyAppApiUserContext
import com.dariusz.twirplyapp.utils.AuthUtils.escapeUrl
import com.dariusz.twirplyapp.utils.AuthUtils.generateRandomString
import com.dariusz.twirplyapp.utils.AuthUtils.getTheSignature
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
        initial: Boolean = false,
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
            initial -> {
                OkHttpClient.Builder().apply {
                    addInterceptor(
                        Interceptor { chain ->
                            val builder = chain.request().newBuilder()
                            builder.header(
                                "Authorization",
                                "OAuth oauth_consumer_key=\"$API_AUTH_CONSUMER_KEY\", " +
                                        "oauth_callback=\"${("example://").escapeUrl()}\", " +
                                        "oauth_nonce=\"${generateRandomString()}\", " +
                                        "oauth_signature=\"${getTheSignature()}\", " +
                                        "oauth_signature_method=\"HMAC-SHA1\", " +
                                        "oauth_timestamp=\"${now()}\", " +
                                        "oauth_token=\"${API_AUTH_ACCESS_TOKEN}\", " +
                                        "oauth_version=\"1.0\""
                            )
                            return@Interceptor chain.proceed(builder.build())
                        }
                    )
                }.addNetworkInterceptor(logging).build()
            }
            userContext -> {
                OkHttpClient.Builder().apply {
                    addInterceptor(
                        Interceptor { chain ->
                            val builder = chain.request().newBuilder()
                            builder.header(
                                "Authorization",
                                "OAuth oauth_consumer_key=\"$API_AUTH_CONSUMER_KEY\", " +
                                        "oauth_nonce=\"${generateRandomString()}\", " +
                                        "oauth_signature=\"${getTheSignature()}\", " +
                                        "oauth_signature_method=\"HMAC-SHA1\", " +
                                        "oauth_timestamp=\"${now()}\", " +
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
    fun provideRetrofitAuthInitial(): TwirplyAppApiAuth =
        provideRetrofitService(
            initial = true
        ).create(TwirplyAppApiAuth::class.java)

    @Provides
    fun provideRetrofitAuth(token: String): TwirplyAppApiAuth =
        provideRetrofitService(
            basic = true,
            authToken = token
        ).create(TwirplyAppApiAuth::class.java)

    @Provides
    fun provideRetrofitUserActions(token: String): TwirplyAppApiUserActions =
        provideRetrofitService(
            userContext = true,
            authToken = token
        ).create(TwirplyAppApiUserActions::class.java)

    @Provides
    fun provideRetrofitUserContext(token: String): TwirplyAppApiUserContext =
        provideRetrofitService(
            userContext = true,
            authToken = token
        ).create(TwirplyAppApiUserContext::class.java)


}