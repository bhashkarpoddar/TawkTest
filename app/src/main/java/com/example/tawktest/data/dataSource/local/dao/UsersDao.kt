package com.example.tawktest.data.dataSource.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.tawktest.data.entity.UsersEntity

@Dao
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

    // Do a similar query as the search API:
    // Look for Users that contain the query string in the name or in the description
    // and order those results ascending, by the name
    @Query("SELECT * FROM USERSENTITY WHERE (login LIKE :queryString) OR (note LIKE " +
            ":queryString) ORDER BY login ASC")
    fun usersBySearch(queryString: String): DataSource.Factory<Int, UsersEntity>
}