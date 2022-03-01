package com.example.tawktest.data.dataSource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tawktest.data.dataSource.local.entity.UsersEntity

interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    suspend fun insertUser(user: UsersEntity)

    @Insert
    suspend fun insertAllUsers(users: MutableList<UsersEntity>)

    @Update
    suspend fun updateUser(user: UsersEntity)

    @Delete
    suspend fun deleteUser(user: UsersEntity)

    @Query("SELECT * FROM UsersEntity")
    fun getAllUsers(): LiveData<MutableList<UsersEntity>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    @Query("DELETE FROM UsersEntity")
    suspend fun clearUser()

    @Query("DELETE FROM UsersEntity WHERE id = :id") //you can use this too, for delete user by id.
    suspend fun deleteUserById(id: Int)
}