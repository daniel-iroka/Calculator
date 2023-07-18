package com.android.calculator.di.modules

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModules {

    // Todo - FIRSTLY, When I come back, I will do as follows :
    //  1. Add dependency for room in this project.
    //  2. Remove the DataStore dependency and the also remove it from our Persistence Module.
    //  3. First, create a "HistoryModel" which will serve as our DataBase Table as well as with its columns.
    //  4. Setup a DAO for our Database.
    //  5. Then instantiate both the Room and Dao in the module.
    //  6. Don't forget to also create a DataBase Abstract class to hold all our tables and our DAO.
    //  7. Then also 'bind' the DataBase class with hilt as well.
    //  8. Then proceed with the continuation of the Room Implementation to store the history of our calculations.

}