package andrewafony.testapp.data.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("tokenManager")
        private val USER_TOKEN_KEY = stringPreferencesKey("access_token")
        private val USER_REFRESH_KEY = stringPreferencesKey("refresh_token")
    }

    val accessToken: Flow<String> =
        context.dataStore.data.map { preferences -> preferences[USER_TOKEN_KEY] ?: "" }

    val refreshToken: Flow<String> =
        context.dataStore.data.map { preferences -> preferences[USER_REFRESH_KEY] ?: "" }

    suspend fun saveToken(token: Token) {
        context.dataStore.edit { preferences ->
            if (token is Token.Access) {
                preferences[USER_TOKEN_KEY] = token.token
            } else {
                preferences[USER_REFRESH_KEY] = token.token
            }
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_TOKEN_KEY)
        }
    }
}

sealed class Token(val token: String) {

    class Refresh(token: String) : Token(token)

    class Access(token: String) : Token(token)
}