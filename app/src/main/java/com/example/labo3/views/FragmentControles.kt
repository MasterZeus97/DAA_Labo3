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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentControles.newInstance] factory method to
 * create an instance of this fragment.
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentControles.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentControles().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}