package com.fathan0041.bukuin_assesment2_fathan.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings_preference"
)
class SettingsDataStore(private val context: Context) {
    companion object {
        private val IS_LIST = booleanPreferencesKey("is_list")
        private val SELECTED_THEME = intPreferencesKey("selected_theme")
    }

    val layoutFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LIST] ?: true
    }

    val selectedThemeFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[SELECTED_THEME] ?: 0
    }


    suspend fun saveLayout(isList:Boolean){
        context.dataStore.edit { preferences ->
            preferences[IS_LIST] = isList
        }
    }

    suspend fun saveTheme(themeId: Int) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_THEME] = themeId
        }
    }

}