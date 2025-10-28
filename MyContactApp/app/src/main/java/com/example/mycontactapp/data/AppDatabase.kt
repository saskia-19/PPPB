package com.example.mycontactapp.data

import android.content.Context
import android.provider.CalendarContract
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [Contact::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {

        @Volatile private var INSTACE: AppDatabase? = null

        fun get(context: Context): AppDatabase = INSTACE ?:synchronized(this) {
            INSTACE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "contacts.db"
            ).build().also { INSTACE = it }
        }

    }
}