package com.android.calculator.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.android.calculator.AppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

// creating an instance of Preferences DataStore
private val Context.dataStorePreferences : DataStore<Preferences> by preferencesDataStore(
    name = "historyPreferences" // this is the name of DataStorePreferences file, where our data will be stored
)

// This Module will serve as a container only for our Jetpack DataStore library.
@Module
@InstallIn(SingletonComponent::class)
class PersistenceModules {

    // The @ApplicationContext annotation just tells here that the context object hold reference to the "ApplicationContext"
    // and not the normal context
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context : Context) : AppDataStore =
        AppDataStore(context.dataStorePreferences)

}