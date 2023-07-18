package com.android.calculator.db.dao

import androidx.room.*
import com.android.calculator.db.entity.CalculatorEntity
import kotlinx.coroutines.flow.Flow

/*
 *  This represents our DAO
 */
@Dao
interface CalculatorDao {

    @Query(value = "SELECT * FROM calculator_entity")
    fun getHistoryList() : Flow<List<CalculatorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistoryList() : List<CalculatorEntity>

    @Delete
    fun deleteHistoryList()

    // Todo - When I come back tomorrow, I will continue the building of this, maybe I will just go with the way it already looks-
   //  todo   which is the new version of room. Maybe that's just how it is.

}