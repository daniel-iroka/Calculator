package com.android.calculator.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/*
 *  This data class represents our CalculatorHistory Table stored in Room Db, as well as its Columns(fields).
 */
@Entity(tableName = "calculator_entity")
data class CalculatorEntity(
    @ColumnInfo(name = "id") @PrimaryKey (autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "primary_state") val historyPrimaryState : String,
    @ColumnInfo(name = "secondary_state") val historySecondaryState : String,
    @ColumnInfo(name = "time") val time : Date = Date()
)