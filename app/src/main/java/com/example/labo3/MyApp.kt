package com.example.labo3

import android.app.Application
import android.content.res.Configuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * @author Perrenoud Pascal
 * @author Seem Thibault
 * @description Permet d'utiliser le repository depuis les fragments et l'activité
 */

class MyApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val repository by lazy {
        val database = MyDatabase.getDatabase(this)
        Repository(database.noteDao(), database.scheduleDao(), database.noteAndScheduleDao(), applicationScope)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}