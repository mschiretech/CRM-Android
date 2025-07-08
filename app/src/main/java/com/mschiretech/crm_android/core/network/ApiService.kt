package com.mschiretech.crm_android.core.network

import com.mschiretech.crm_android.core.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //Find user by email
    @GET("users")
    suspend fun searchUserByEmail(@Query("email") email: String): List<User>

    //Post a new user
    @POST("users")
    suspend fun createUser(@Body user: User): User

    //Get all users
    @GET("users")
    suspend fun getAllUsers(): List<User>

}