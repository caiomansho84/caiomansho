package com.example.caiomansho.data.datasource

import android.content.SharedPreferences
import com.example.caiomansho.data.repository.UserRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): UserRepository {
    override fun login(username: String, password: String): Boolean {
        if(username == "caio" && password == "1234") {
            editor.putString("auth_token", UUID.randomUUID().toString())
            editor.putString("username", username)
            editor.putFloat("balance", 1000.0f)
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
        return prefs.getString("username", "")!!
    }

}