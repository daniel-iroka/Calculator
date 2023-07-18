package com.android.calculator.db

import com.android.calculator.db.dao.CalculatorDao
import com.android.calculator.db.entity.CalculatorEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Using the repository pattern to create an Abstraction between our Database and the rest of our App
class CalculatorRepository @Inject constructor(private val dao : CalculatorDao) {

    fun getHistoryList() : Flow<List<CalculatorEntity>> =
        dao.getHistoryList()

    fun insertHistoryList(historyList : List<CalculatorEntity>) =
        dao.insertHistoryList(historyList)

    fun deleteList() = dao.deleteList()

}