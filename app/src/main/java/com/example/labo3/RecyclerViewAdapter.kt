package com.example.labo3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.labo3.models.Note
import com.example.labo3.models.NoteAndSchedule
import com.example.labo3.models.Type
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Collections
import java.util.Locale
import java.util.concurrent.TimeUnit

class RecyclerViewAdapter(_items : List<NoteAndSchedule> = listOf()) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var items = listOf<NoteAndSchedule>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    init {
        items = _items
    }

    fun creationSort():Boolean{
        items = items.sortedByDescending {
            it.note.creationDate
        }
        return true
    }

    fun scheduleSort():Boolean{
        items.sortedByDescending {
            it.schedule?.date
        }
        return true
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        if(items[position] is NoteAndSchedule) return NOTE_SCHEDULE
        else return ERROR
    }

    companion object{
        private val ERROR = -1
        private val NOTE_SCHEDULE = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.schedule_note_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        private val noteIcon = view.findViewById<ImageView>(R.id.note_icon)
        private val noteTitle = view.findViewById<TextView>(R.id.note_title)
        private val noteDesc = view.findViewById<TextView>(R.id.note_resume)
        private val noteClock = view.findViewById<ImageView>(R.id.note_clock)
        private val noteTimeLeft = view.findViewById<TextView>(R.id.note_time_left)

        fun bind(note : Note){
            when (note.type){
                Type.FAMILY -> noteIcon.setImageResource(R.drawable.family)
                Type.TODO -> noteIcon.setImageResource(R.drawable.todo)
                Type.SHOPPING -> noteIcon.setImageResource(R.drawable.shopping)
                Type.WORK ->noteIcon.setImageResource(R.drawable.work)
                Type.NONE -> noteIcon.setImageResource(R.drawable.note)
            }
            noteTitle.text = note.title
            noteDesc.text = note.text
        }

        fun bind(note : NoteAndSchedule){
            bind(note.note)

            if(note.schedule != null) {

                noteClock.visibility = View.VISIBLE

                val tmp = note.schedule.date.time.time
                val tmp2 = Calendar.getInstance().time.time

                val tmp3 = tmp - tmp2

                val diffInDay = TimeUnit.MILLISECONDS.toDays(tmp3)
                val diffInYear = diffInDay / 365
                val diffInMonth = diffInDay / 30
                val diffInWeek = diffInDay / 7
                val diffInHours = TimeUnit.MILLISECONDS.toHours(tmp3)

                if(diffInYear > 0){
                    noteTimeLeft.text = "$diffInYear years"
                }else if(diffInMonth > 0){
                    noteTimeLeft.text = "$diffInMonth months"
                }else if(diffInWeek > 0){
                    noteTimeLeft.text = "$diffInWeek weeks"
                }else if(diffInDay > 0){
                    noteTimeLeft.text = "$diffInDay days"
                }else{
                    noteTimeLeft.text = "$diffInHours hours"
                }
            }else{
                noteClock.visibility = View.INVISIBLE
                noteTimeLeft.text = ""
            }
        }
    }
}