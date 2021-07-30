package com.dariusz.twirplyapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface AppPreferences {

    suspend fun saveIDOfLoggedInUser(userID: Int)

    suspend fun getIDOfLoggedInUser(): Flow<Int>

    suspend fun removeIDOfLoggedInUser()

    suspend fun saveLoginStatus(status: Boolean = false)

    suspend fun getLoginStatus(): Flow<Boolean>

    suspend fun saveBearerToken(token: String)

    suspend fun getBearerToken(): Flow<String>

    suspend fun removeBearerToken()

    suspend fun removeLoginStatus()

}

class AppPreferencesImpl
@Inject constructor(
    context: Context
) : AppPreferences {

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(name = "main_preferences")
    private val dataStore = context._dataStore

    private val userIDPreferencesKey = intPreferencesKey("logged_in_user_id")
    private val isUserLoggedInKey = booleanPreferencesKey("login_status")
    private val tempBearerToken = stringPreferencesKey("bearer_token")

    override suspend fun saveIDOfLoggedInUser(userID: Int) {
        dataStore.edit { preferences ->
            preferences[userIDPreferencesKey] = userID
        }
    }

    override suspend fun removeIDOfLoggedInUser() {
        dataStore.edit { preferences ->
            preferences[userIDPreferencesKey] = 0
        }
    }

    override suspend fun getIDOfLoggedInUser() =
        dataStore.data.map { preferences ->
            preferences[userIDPreferencesKey] ?: 0
        }

    override suspend fun saveLoginStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[isUserLoggedInKey] = status
        }
    }

    override suspend fun getLoginStatus() =
        dataStore.data.map { preferences ->
            preferences[isUserLoggedInKey] ?: false
        }


    override suspend fun removeLoginStatus() {
        dataStore.edit { preferences ->
            preferences[isUserLoggedInKey] = false
        }
    }

    override suspend fun saveBearerToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tempBearerToken] = token
        }
    }

    override suspend fun getBearerToken() =
        dataStore.data.map { preferences ->
            preferences[tempBearerToken] ?: ""
        }

    override suspend fun removeBearerToken() {
        dataStore.edit { preferences ->
            preferences[tempBearerToken] = ""
        }
    }

}