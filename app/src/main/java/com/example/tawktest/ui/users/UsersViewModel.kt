package com.example.tawktest.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tawktest.data.dataSource.remote.Resource
import com.example.tawktest.data.dataSource.repository.UsersRepository
import kotlinx.coroutines.Dispatchers

class UsersViewModel(private val usersRepository: UsersRepository): ViewModel() {

    init {

    }

    fun getUsers(lastId: Int = 0) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = usersRepository.getUsers(lastId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}