package com.example.labo3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.labo3.dao.CalendarConverter
import com.example.labo3.models.Note
import com.example.labo3.models.Schedule
import kotlin.concurrent.thread

@Database(entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true)
@TypeConverters(CalendarConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun noteDao() : com.example.labo3.dao.Note
    abstract fun scheduleDao() : com.example.labo3.dao.Schedule
    companion object {
        private var INSTANCE : MyDatabase? = null
        fun getDatabase(context: Context) : MyDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MyDatabase::class.java, "mydatabase.db")
                    // .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .addCallback(MyDatabaseCallBack())
                    .build()
                INSTANCE!!
            }
        }
    }

    private class MyDatabaseCallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {database ->
                if (database.noteDao().count().value == 0) {
                    thread {
                        // TODO : Populate la DB
                    }
                }
            }
        }
        override fun onOpen(db: SupportSQLiteDatabase) { super.onOpen(db) }
        override fun onDestructiveMigration(db: SupportSQLiteDatabase) { super.onDestructiveMigration(db) }
    }
}
