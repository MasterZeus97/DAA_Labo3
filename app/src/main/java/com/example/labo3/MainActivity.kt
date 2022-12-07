package com.example.labo3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels{
        MyViewModelFactory((application as MyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.sort_creation->{
                viewModel.sortEnum.value = MyViewModel.EnumSort.CREATION_SORT
                true}
            R.id.sort_schedule->{
                viewModel.sortEnum.value = MyViewModel.EnumSort.SCHEDULE_SORT
                true}
            R.id.menu_generate->{viewModel.generateANote(); return true}
            R.id.menu_delete->{viewModel.deleteAllNote(); return true}
            else->super.onOptionsItemSelected(item)
        }
    }
}