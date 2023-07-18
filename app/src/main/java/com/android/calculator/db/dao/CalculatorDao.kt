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
    fun insertHistoryList(historyList : List<CalculatorEntity>)

    @Delete
    fun deleteList()
}