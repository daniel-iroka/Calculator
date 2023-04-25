package com.android.calculator.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    // Todo - When I come back, I will continue in the Implementation of Dependency Injection using Hilt in my Project. I will first "provide" the dependency for Jetpack DataStore
    // todo   I may summarise/go through again what how Dependency Injection with hilt works.

    @Provides
    fun provideDataStorePreferences() {
        // Do something...
    }
}