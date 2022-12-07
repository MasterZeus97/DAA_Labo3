package com.example.labo3.dao

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Perrenoud Pascal
 * @author Seem Thibault
 * @description Converter pour le stockage dans la DB des Calendar
 */

class CalendarConverter {
    @TypeConverter
    fun toCalendar(dateLong: Long): Calendar = Calendar.getInstance().apply { time = Date(dateLong) }

    @TypeConverter
    fun fromCalendar(date: Calendar) = date.time.time
}
