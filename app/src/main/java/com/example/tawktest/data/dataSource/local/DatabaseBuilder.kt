package com.example.tawktest.data.dataSource.local

import android.content.Context
import androidx.room.Room
import com.example.tawktest.app.AppData.DB_NAME

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}