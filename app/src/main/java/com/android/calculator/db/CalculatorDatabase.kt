package com.android.calculator.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.calculator.db.dao.CalculatorDao
import com.android.calculator.db.entity.CalculatorEntity

@Database(entities = [CalculatorEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun dao() : CalculatorDao
}
