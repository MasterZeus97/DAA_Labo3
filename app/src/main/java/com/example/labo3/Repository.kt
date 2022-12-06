package com.example.labo3

import androidx.lifecycle.LiveData
import com.example.labo3.models.Note
import com.example.labo3.models.NoteAndSchedule
import com.example.labo3.models.Schedule
import kotlin.concurrent.thread

class Repository(
    private val note: com.example.labo3.dao.Note,
    private val schedule: com.example.labo3.dao.Schedule,
    private val noteAndSchedule: com.example.labo3.dao.NoteSchedule
    ) {
    fun deleteAll() {
        thread {
            note.deleteAll()
            schedule.deleteAll()
        }
    }

    fun addNote(n: Note): Long {
        // TODO : Should be done in a thread ?
        return note.insert(n)
    }

    fun addSchedule(s: Schedule) : Long {
        // TODO : Should be done in a thread ?
        return schedule.insert(s)
    }

    val countNotes: LiveData<Int>
        get() {
            // TODO : Should be done in a thread ?
            return note.count()
        }
    val allNotes: LiveData<List<NoteAndSchedule>>
        get() {
            // TODO : Should be done in a thread ?
            return noteAndSchedule.get()
        }
}