package com.example.tawktest.data.dataSource.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.tawktest.data.dataSource.local.dao.UsersDao
import com.example.tawktest.data.dataSource.remote.ApiService
import com.example.tawktest.data.dataSource.remote.Resource
import com.example.tawktest.data.entity.UsersEntity
import kotlinx.coroutines.Dispatchers

class UsersRepository(private val apiService: ApiService, private val usersDao: UsersDao) {
    /*https://www.raywenderlich.com/12244218-paging-library-for-android-with-kotlin-creating-infinite-lists*/

//    val newsList = resultLiveData(
//        /** Showing old data from DB while fetching fresh one from remote */
//        databaseQuery = { usersDao.getAllUsers() },
//        /** Fetching fresh data from remote
//         * In no internet scenario this will fail,
//         * and data from local DB will be continued to show to user */
//        networkCall = { apiService.getAllUsers(0) },
//        /** When API calls succeed this fresh data is saved to DB
//         * Upon data update in DB, its listners are notified automatically as it wrapped in live data
//         * See return type of insertAll() in NewsDAO */
//        saveCallResult = { usersListResponse ->
//            usersListResponse?.apply { usersDao.insertAllUsers(usersListResponse) }
//        })

    fun getAllUsersData(lastId: Int, size: Int = 10) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        val source = allUsers.map { Resource.success(data = it) }
        emitSource(source)

        try {
            val usersListResponse = getAllUsers(lastId)
            usersListResponse.apply { insertAllUsers(this) }
            /*emit(Resource.success(data = usersListResponse))*/
        } catch (exception: Exception){
            exception.printStackTrace()
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            emitSource(source)
        }
    }

    /**Remote Data Source*/

    suspend fun getAllUsers(lastId: Int) = apiService.getAllUsers(lastId)

    /**Local Data Source*/

    suspend fun insert(user: UsersEntity) = usersDao.insertUser(user)

    suspend fun delete(user: UsersEntity) = usersDao.deleteUser(user)

    suspend fun deleteUserById(id: Int) = usersDao.deleteUserById(id)

    suspend fun update(user: UsersEntity) = usersDao.updateUser(user)

    suspend fun insertAllUsers(users: MutableList<UsersEntity>) = usersDao.insertAllUsers(users)

    val allUsers : LiveData<MutableList<UsersEntity>> = usersDao.getAllUsers()

}