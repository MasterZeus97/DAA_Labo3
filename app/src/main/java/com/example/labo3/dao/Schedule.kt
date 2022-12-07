package com.example.labo3.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

/**
 * @author Perrenoud Pascal
 * @author Seem Thibault
 * @description DAO pour les schedule
 */

@Dao
interface Schedule {
    @Insert
    fun insert(schedule: com.example.labo3.models.Schedule) : Long

    @Update
    fun update(schedule: com.example.labo3.models.Schedule)

    @Delete
    fun delete(schedule: com.example.labo3.models.Schedule)

    @Query("DELETE FROM Schedule")
    fun deleteAll()

    @Query("SELECT * FROM Schedule")
    fun getAllSchedules() : LiveData<List<com.example.labo3.models.Schedule>>

    @Query("SELECT * FROM Schedule WHERE scheduleId = :scheduleId")
    fun getNoteById(scheduleId: Long) : com.example.labo3.models.Schedule
}