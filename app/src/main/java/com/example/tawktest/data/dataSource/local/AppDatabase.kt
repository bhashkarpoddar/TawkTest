package com.example.tawktest.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tawktest.data.dataSource.local.dao.UsersDao
import com.example.tawktest.data.entity.UsersEntity

@Database(
    entities = [UsersEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUsersDao(): UsersDao

}