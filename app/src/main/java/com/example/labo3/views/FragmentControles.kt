package com.example.labo3.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.labo3.MyApp
import com.example.labo3.MyViewModel
import com.example.labo3.MyViewModelFactory
import com.example.labo3.R

/**
 * @author Perrenoud Pascal
 * @author Seem Thibault
 * @description Fragment pour afficher les boutons et le nombre de note sur une tablette en mode
 * paysage
 */

class FragmentControles : Fragment() {

    lateinit var btnGenerate: Button
    lateinit var btnDelete: Button
    lateinit var counter: TextView
    private val myViewModel: MyViewModel by activityViewModels{
        MyViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGenerate = view.findViewById<Button>(R.id.btn_generate)
        btnDelete = view.findViewById<Button>(R.id.btn_delete)
        counter = view.findViewById<TextView>(R.id.notes_count)

        btnGenerate.setOnClickListener{
            myViewModel.generateANote()
        }

        btnDelete.setOnClickListener{
            myViewModel.deleteAllNote()
        }

        counter.text = myViewModel.countNotes.value.toString()

        myViewModel.countNotes.observe(viewLifecycleOwner){
            counter.text = myViewModel.countNotes.value.toString()
        }
    }
}