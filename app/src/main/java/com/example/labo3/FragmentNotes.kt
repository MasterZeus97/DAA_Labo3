package com.example.labo3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentNotes.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNotes : Fragment() {

    lateinit var adapter: RecyclerViewAdapter

    private val myViewModel: MyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_view_notes)

        adapter = RecyclerViewAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(activity)

        myViewModel.allNotes.observe(viewLifecycleOwner){
            adapter.items = myViewModel.allNotes.value!!

            when(myViewModel.sortEnum.value){
                MyViewModel.EnumSort.CREATION_SORT->{
                    adapter.creationSort()
                }
                MyViewModel.EnumSort.SCHEDULE_SORT->{
                    adapter.scheduleSort()
                }
                else -> {}
            }

        }

        myViewModel.sortEnum.observe(viewLifecycleOwner){
            when(myViewModel.sortEnum.value){
                MyViewModel.EnumSort.CREATION_SORT->{
                    adapter.creationSort()
                }
                MyViewModel.EnumSort.SCHEDULE_SORT->{
                    adapter.scheduleSort()
                }
                else -> {}
            }
        }
    }
}