package com.dariusz.twirplyapp.di

import android.content.Context
import com.dariusz.twirplyapp.data.local.AppPreferences
import com.dariusz.twirplyapp.data.local.AppPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences =
        AppPreferencesImpl(context)

}