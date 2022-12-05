package com.example.labo3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ch.heigvd.iict.and.labo4.models.Note
import ch.heigvd.iict.and.labo4.models.NoteAndSchedule

class ViewModelTMP : ViewModel(){

    enum class EnumTest {
        BLA,
        BLO,
        BLU
    }

    val allNotes = MutableLiveData<List<NoteAndSchedule>>(listOf( NoteAndSchedule(Note.generateRandomNote(),
        Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule()),
        NoteAndSchedule(Note.generateRandomNote(),
            Note.generateRandomSchedule())))

    val numNote = MutableLiveData(allNotes.value?.size)

    fun generateANote(){

    }

    fun deleteAllNote(){

    }

    val sortEnum = MutableLiveData<EnumTest>(EnumTest.BLU)

}