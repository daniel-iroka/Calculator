package com.android.calculator.di.modules

import android.content.Context
import androidx.room.Room
import com.android.calculator.db.CalculatorDatabase
import com.android.calculator.db.dao.CalculatorDao
import com.android.calculator.utils.CalculatorHistoryConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModules {

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

    @Provides
    fun provideCalculatorConverter() = CalculatorHistoryConverter()

}