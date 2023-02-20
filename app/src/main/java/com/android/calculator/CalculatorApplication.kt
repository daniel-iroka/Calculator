package com.android.calculator

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

// IMPORTANT TEMP NOTE: This is where we will Initialize classes that we would want to startup as our App starts up because
//                      this class holds reference to our "Application" class.

class CalculatorApplication : Application() {

    companion object {
        lateinit var dataStore : DataStore<Preferences>
    }

    override fun onCreate() {
        super.onCreate()
        // Do something later...
    }
}