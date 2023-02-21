package com.android.calculator

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

// IMPORTANT TEMP NOTE: This is where we will Initialize classes that we would want to startup as our App starts up because
//                      this class holds reference to our "Application" class.

class CalculatorApplication : Application() {

    // This is where we create an instance of PreferencesDataStore by using the preferencesDataStore delegate...
    private val Context.dataStorePreferences : DataStore<Preferences> by preferencesDataStore(
        name = "history_preferences"
    )

    companion object {
        lateinit var dataStore : DataStorePreferences // initializing our DataStorePreferences class
    }

    override fun onCreate() {
        super.onCreate()

        dataStore = DataStorePreferences(dataStorePreferences)
    }
}