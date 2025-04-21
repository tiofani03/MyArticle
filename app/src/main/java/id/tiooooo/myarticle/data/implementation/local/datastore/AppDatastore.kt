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
    }

    suspend fun setNewsSite(data: String) {
        context.dataStore.edit { prefs ->
            prefs[NEWS_SITE] = data
        }
    }

    val newsSite: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[NEWS_SITE].orEmpty()
        }
}