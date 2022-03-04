package com.example.tawktest.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tawktest.data.dataSource.local.dao.UsersDao
import com.example.tawktest.data.dataSource.remote.Resource
import com.example.tawktest.data.dataSource.repository.UsersRepository
import com.example.tawktest.data.entity.UsersEntity
import kotlinx.coroutines.Dispatchers

class UsersViewModel(private val usersRepository: UsersRepository): ViewModel() {

    init {

    }

    val usersList: LiveData<Resource<MutableList<UsersEntity>>> = usersRepository.getAllUsersData(0)

    fun getUsers(lastId: Int = 0) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = usersRepository.getAllUsers(lastId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}