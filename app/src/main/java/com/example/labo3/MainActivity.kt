package com.example.labo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    lateinit var frag: FragmentContainerView
    private val viewModel: ViewModelTMP by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frag = findViewById<FragmentContainerView>(R.id.note_frag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_smarphone,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.sort_creation->{
                viewModel.sortEnum.value = ViewModelTMP.EnumTest.BLA
                true}
            R.id.sort_schedule->{
                viewModel.sortEnum.value = ViewModelTMP.EnumTest.BLO
                true}
            R.id.menu_generate->{viewModel.generateANote(); return true}
            R.id.menu_delete->{viewModel.deleteAllNote(); return true}
            else->super.onOptionsItemSelected(item)
        }
    }
}