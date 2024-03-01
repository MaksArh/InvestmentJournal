package com.example.InvestmentJournal.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.InvestmentJournal.models.SharedPreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(private val context: Context) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return SharedPreferencesManager.isLoggedIn()
    }

    fun performLogin(username: String, password: String, navController: NavController) {
        RetrofitInstance.api.login(User(username, password)).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        SharedPreferencesManager.setLoggedIn(true)
                        SharedPreferencesManager.setUser(it.id) // Сохранение имени пользователя
                        navController.navigate("MainScreenRoute")
                    }
                } else {
                    SharedPreferencesManager.setLoggedIn(true)
                    navController.navigate("MainScreenRoute")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                SharedPreferencesManager.setLoggedIn(true)
                navController.navigate("MainScreenRoute")
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
