package com.android.calculator.db

import androidx.room.TypeConverter
import java.util.Date

// This our Type converter's file.
class DateTypeConverter {
    @TypeConverter
    fun fromDateToLong(date : Date?) : Long? {
        return date?.time
    }

    @TypeConverter
    fun fromLongToDate(timeStamp : Long?) : Date? {
        return timeStamp?.let { Date(it) }
    }
}