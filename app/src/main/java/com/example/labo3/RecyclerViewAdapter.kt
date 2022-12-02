package com.example.labo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.iict.and.labo4.models.Note
import ch.heigvd.iict.and.labo4.models.Type

class RecyclerViewAdapter(_items : List<Note> = listOf()) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var items = listOf<Note>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    init {
        items = _items
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        if(true) return NOTE
        else return ERROR
    }

    companion object{
        private val ERROR = -1
        private val NOTE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false))
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
                Type.NONE -> {}
            }
            noteTitle.text = note.title
            noteDesc.text = note.text
        }
    }
}