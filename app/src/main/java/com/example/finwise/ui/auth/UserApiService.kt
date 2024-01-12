package com.example.finwise.ui.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("users/login")
    fun login(@Body userData: User): Call<UserResponse>

    @POST("users/register")
    fun register(@Body userData: User): Call<UserResponse>

    @GET("forum")
    suspend fun getAllMessages(): List<MessageDto>

    @POST("forum")
    fun sendMessage(@Body message: MessageDto): Call<Unit>
}

data class MessageDto(
    val userName: String,
    val message: String
)

data class User(
    val name: String,
    val password: String
)

data class UserResponse(
    val name: String // Или другие поля, которые возвращает ваш API
)
