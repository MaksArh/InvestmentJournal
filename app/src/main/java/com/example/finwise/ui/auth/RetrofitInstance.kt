package com.example.finwise.ui.auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: UserApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.108:3000/") // Используйте 10.0.2.2 для локального сервера на эмуляторе
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
}
