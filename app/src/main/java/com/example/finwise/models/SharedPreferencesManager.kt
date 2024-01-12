package com.example.finwise.models

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREF_NAME = "auth_prefs"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    private const val KEY_USERNAME = "username"
    private const val L1 = 0f
    private const val L2 = 0f
    private const val L3 = 0f
    private const val L4 = 0f
    private const val L5 = 0f
    private const val IS_CHANGED = "false"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setChangeFlag(){
        sharedPreferences.edit().putBoolean(IS_CHANGED,true).apply()
    }

    fun getChangeFlag(): Boolean{
        return sharedPreferences.getBoolean(IS_CHANGED,false)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(loggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply()
    }

    fun setUsername(username: String) {
        sharedPreferences.edit().putString(KEY_USERNAME, username).apply()
    }

    fun getUsername(): String {
        return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    }

    fun setProgress(lesson: String, value: Float) {
        sharedPreferences.edit().putFloat(lesson, value).apply()
    }

    fun getProgress(lesson: String): Float {
        return sharedPreferences.getFloat(lesson, 0f) // Здесь возвращается значение Float
    }
}
