package com.example.InvestmentJournal.models

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREF_NAME = "auth_prefs"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    private const val KEY_USERNAME = "userId"
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

    fun setUser(userId: Int) {
        sharedPreferences.edit().putInt(KEY_USERNAME, userId).apply()
    }

    fun getUser(): Int {
        return sharedPreferences.getInt(KEY_USERNAME, -1)
    }

    fun setProgress(lesson: String, value: Float) {
        sharedPreferences.edit().putFloat(lesson, value).apply()
    }

    fun getProgress(lesson: String): Float {
        return sharedPreferences.getFloat(lesson, 0f) // Здесь возвращается значение Float
    }
}
