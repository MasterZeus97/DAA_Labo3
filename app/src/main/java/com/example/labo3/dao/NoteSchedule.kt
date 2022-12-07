package com.example.labo3.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.labo3.models.NoteAndSchedule

@Dao
interface NoteSchedule {
    fun deleteAll() {
        deleteAllNotes()
        deleteAllSchedules()
    }

    @Query("DELETE FROM Note") fun deleteAllNotes()
    @Query("DELETE FROM Schedule") fun deleteAllSchedules()

    @Query("SELECT COUNT(*) FROM Note") fun count(): LiveData<Int>

    @Query("SELECT * FROM Note AS n LEFT JOIN Schedule AS s ON s.ownerId = n.noteId ORDER BY s.date ASC")
    fun get(): LiveData<List<NoteAndSchedule>>
}