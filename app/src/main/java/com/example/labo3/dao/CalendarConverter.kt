package com.example.labo3.dao

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {
    @TypeConverter
    fun toCalendar(dateLong: Long): Calendar = Calendar.getInstance().apply { time = Date(dateLong) }

    @TypeConverter
    fun fromCalendar(date: Calendar) = date.time.time
}
