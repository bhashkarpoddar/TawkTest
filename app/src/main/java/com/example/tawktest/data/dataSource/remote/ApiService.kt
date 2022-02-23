package com.example.tawktest.data.dataSource.remote

import com.example.tawktest.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(@Query("since") lastId: Int): MutableList<User>

}