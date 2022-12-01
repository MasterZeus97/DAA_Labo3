package com.example.labo3

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.iict.and.labo4.models.Note

class RecyclerViewAdapter(_items : List<Note> = listOf()) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var items = listOf<Note>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    init {
        items = _items
    }

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = -1L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        private val nameMammal =
    }
}