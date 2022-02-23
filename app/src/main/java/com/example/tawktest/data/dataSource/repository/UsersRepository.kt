package com.example.tawktest.data.dataSource.repository

import com.example.tawktest.data.dataSource.remote.ApiService

class UsersRepository(private val apiService: ApiService) {

    suspend fun getUsers(lastId: Int) = apiService.getUsers(lastId)

}