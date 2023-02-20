package com.android.calculator

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.android.calculator.models.CalculatorHistoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/** This is file where we will store our User's results in form of key-value pairs using DataStore Preferences. **/

val PRIMARY_KEY = stringPreferencesKey("history_key")
val SECONDARY_KEY = stringPreferencesKey("history_key")

class DataStorePreferences(private val dataStore : DataStore<Preferences>) {

    // Todo - When I come back, we will continue Implementing the functions that will use to READ and WRITE data to our Preferences in-
    //  form of key-value pairs(NOTE : We will also do this using Kotlin Coroutines and flows.)

    val getSavedHistory : Flow<CalculatorHistoryState> = dataStore.data
        .map { preferences ->
            // Todo - When I come back, I am not sure if this will be the right Implementation for this but I will continue looking into it.
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