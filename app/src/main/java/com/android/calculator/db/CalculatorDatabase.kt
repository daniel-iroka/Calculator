package com.android.calculator.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.calculator.db.dao.CalculatorDao
import com.android.calculator.db.entity.CalculatorEntity

@Database(entities = [CalculatorEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun dao() : CalculatorDao
}
