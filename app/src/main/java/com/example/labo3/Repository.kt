package com.example.labo3

import androidx.lifecycle.LiveData
import com.example.labo3.dao.NoteSchedule
import com.example.labo3.models.Note
import com.example.labo3.models.NoteAndSchedule
import com.example.labo3.models.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingQueue

/**
 * @author Perrenoud Pascal
 * @author Seem Thibault
 * @description Gère les interactions avec les DAO en les séparant du thread MAIN
 */

class Repository(
    private val note: com.example.labo3.dao.Note,
    private val schedule: com.example.labo3.dao.Schedule,
    private val noteAndSchedule: NoteSchedule,
    private val scope: CoroutineScope
) {
    fun deleteAll() {
        scope.launch(Dispatchers.IO) {
            note.deleteAll()
            schedule.deleteAll()
        }
    }

    fun addNote(n: Note): LinkedBlockingQueue<Long> {
        val queue = LinkedBlockingQueue<Long>()
        scope.launch(Dispatchers.IO) {
            queue.put(note.insert(n))
        }
        return queue
    }

    fun addSchedule(s: Schedule) {
        scope.launch(Dispatchers.IO) {
            schedule.insert(s)
        }
    }

    val countNotes: LiveData<Int>
        get() {
            return note.count()
        }
    val allNotes: LiveData<List<NoteAndSchedule>>
        get() {
            return noteAndSchedule.get()
        }
}