package com.android.calculator.di.modules

import android.content.Context
import androidx.room.Room
import com.android.calculator.db.CalculatorDatabase
import com.android.calculator.db.dao.CalculatorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModules {

    // Todo - FIRSTLY, When I come back, I will do as follows :
    //  1. Add dependency for room in this project. ✔
    //  2. Remove the DataStore dependency and the also remove it from our Persistence Module. ✔
    //  3. First, create a "HistoryModel" which will serve as our DataBase Table as well as with its columns. ✔
    //  4. Setup a DAO for our Database. ✔
    //  5. Then instantiate both the Room and Dao in the module. ✔
    //  6. Don't forget to also create a DataBase Abstract class to hold all our tables and our DAO. ✔
    //  7. Then also provide the DataBase class with hilt as well. ✔
    //  8. Then proceed with the continuation of the Room Implementation to store the history of our calculations.

    // Todo - When I come back tomorrow, I will continue in the Implementation of Room into our Project.

    /*
     *  Here we are providing the Instances of our Project's Database(CalculatorDatabase), Room Database and our DAO.
     */
    @Provides
    fun provideRoomDatabase(@ApplicationContext context : Context) : CalculatorDatabase =
        Room.databaseBuilder(
            context,
            CalculatorDatabase::class.java,
            "calculator_database"
        ).build()

    @Provides
    fun provideDao(db : CalculatorDatabase) : CalculatorDao =
        db.dao()

}