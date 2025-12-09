package com.example.caiomansho.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.example.caiomansho.data.repository.LoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSource @Inject constructor(
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): LoginRepository {
    override fun login(username: String, password: String): Boolean {
        if(username == "caio" && password == "1234") {
            editor.putString("auth_token", UUID.randomUUID().toString())
            editor.putString("username", username)
            editor.apply()
            return true
        } else {
            return false
        }
    }

    override fun isLogged(): Boolean {
        return prefs.contains("auth_token")
    }

    override fun getUsername(): String {
        return prefs.getString("username", null) ?: ""
    }
}