package id.tiooooo.myarticle.data.implementation.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import id.tiooooo.myarticle.utils.AppConstants.PREF_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatastore(
    private val context: Context,
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREF_NAME)

    companion object {
        val NEWS_SITE = stringPreferencesKey("IS_ALREADY_LOADED")
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val USER_NICK_NAME = stringPreferencesKey("USER_NICK_NAME")
        val LAST_LOGIN = stringPreferencesKey("LAST_LOGIN")
        val IS_LOGGED_IN = stringPreferencesKey("IS_LOGGED_IN")
    }

    suspend fun setNewsSite(data: String) {
        context.dataStore.edit { prefs ->
            prefs[NEWS_SITE] = data
        }
    }

    suspend fun setUserEmail(
        data: String,
        lastLogin: String,
    ) {
        context.dataStore.edit { prefs ->
            prefs[USER_EMAIL] = data
            prefs[LAST_LOGIN] = lastLogin
        }
    }

    suspend fun setUserNickName(data: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_NICK_NAME] = data
        }
    }

    suspend fun clearUserEmail() {
        context.dataStore.edit { prefs ->
            prefs[USER_EMAIL] = ""
            prefs[LAST_LOGIN] = ""
        }
    }

    suspend fun setIsLoggedIn(data: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = data.toString()
        }
    }


    val userEmail: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[USER_EMAIL].orEmpty()
        }

    val lastLogin: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[LAST_LOGIN].orEmpty()
        }

    val userNickName: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[USER_NICK_NAME].orEmpty()
        }

    val newsSite: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[NEWS_SITE].orEmpty()
        }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { prefs ->
            prefs[IS_LOGGED_IN]?.toBoolean() ?: false
        }
}