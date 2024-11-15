package org.sopt.and.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenLocalDataSource @Inject constructor(
    private val context: Context
) {

    private val Context.dataStore by preferencesDataStore(name = TOKEN_DATASTORE_NAME)

    suspend fun saveToken(token: String) = context.dataStore.edit { preferences ->
        preferences[TOKEN_KEY] = token
    }

    fun getToken(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    suspend fun deleteToken() = context.dataStore.edit { preferences ->
        preferences.remove(TOKEN_KEY)
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token_key")
        private const val TOKEN_DATASTORE_NAME = "token_preferences"
    }
}

