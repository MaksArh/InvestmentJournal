package com.example.finwise.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.finwise.models.SharedPreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AuthViewModel(private val context: Context) : ViewModel() {

//    private val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return SharedPreferencesManager.isLoggedIn()
    }

    fun performLogin(username: String, password: String, navController: NavController) {
        RetrofitInstance.api.login(User(username, password)).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        SharedPreferencesManager.setLoggedIn(true)
                        SharedPreferencesManager.setUsername(it.name) // Сохранение имени пользователя
                        navController.navigate("MainScreenRoute")
                    }
                } else {
                    // TODO: Обработ ошибку логина
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // TODO: Обработ сбой сети или другую ошибку
            }
        })
    }

    fun performRegistration(username: String, password: String, navController: NavController) {
        RetrofitInstance.api.register(User(username, password)).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    navController.navigate("LoginScreenRoute")
                } else {
                    // TODO: Обработ ошибку логина
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // TODO: Обработ сбой сети или другую ошибку
            }
        })
    }
}
