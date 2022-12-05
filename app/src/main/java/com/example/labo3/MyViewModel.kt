package com.example.labo3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labo3.models.Note
import java.util.*

class MyViewModel(private val repository: Repository) : ViewModel() {
    val allNotes = repository.allNotes
    val countNotes = repository.countNotes

    fun generateANote() {
        val rand = Random()
        val noteId = repository.addNote(Note.generateRandomNote())
        if (rand.nextBoolean()) {
            val schedule = Note.generateRandomSchedule()
            schedule.ownerId = noteId
            repository.addSchedule(schedule)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun populate() {
        val rand = Random()
        for (i in 0 until rand.nextInt(10)+10) {
            generateANote()
        }
    }

    fun deleteAllNote() {
        repository.deleteAll()
    }
}

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
