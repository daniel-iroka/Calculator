package com.android.calculator

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.android.calculator.models.CalculatorHistoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/** This is file where we will store our User's results in form of key-value pairs using AppDataStore Preferences. **/

val PRIMARY_KEY = stringPreferencesKey("primary_key")
val SECONDARY_KEY = stringPreferencesKey("secondary_key")

class AppDataStore(private val dataStore : DataStore<Preferences>) {

    fun getSavedHistory() : Flow<CalculatorHistoryState> = dataStore.data
        .map { preferences ->
            CalculatorHistoryState(
                historyPrimaryState = preferences[PRIMARY_KEY] ?: "",
                historySecondaryState = preferences[SECONDARY_KEY] ?: ""
            )
        }

    suspend fun saveHistory(historyState : CalculatorHistoryState) {
        dataStore.edit { preferences ->
            preferences[PRIMARY_KEY] = historyState.historyPrimaryState
            preferences[SECONDARY_KEY] = historyState.historySecondaryState
        }
    }
}