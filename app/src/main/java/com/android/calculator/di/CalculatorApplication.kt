package com.android.calculator.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.android.calculator.DataStorePreferences
import dagger.hilt.android.HiltAndroidApp

// IMPORTANT TEMP NOTE: This is where we will Initialize classes that we would want to startup as our App starts up because
//                      this class holds reference to our "Application" class.

@HiltAndroidApp
class CalculatorApplication : Application() {

    // Todo - Continue Implementing Dependency injection with this too.

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