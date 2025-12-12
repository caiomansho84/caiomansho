package com.example.caiomansho.data.datasource

import android.content.SharedPreferences
import com.example.caiomansho.data.repository.UserRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

const val AUTH_TOKEN = "auth_token"
const val USERNAME = "username"
const val BALANCE = "balance"
@Singleton
class UserDataSource @Inject constructor(
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): UserRepository {
    override fun login(username: String, password: String): Boolean {
        if(username == "caio" && password == "1234") {
            editor.putString(AUTH_TOKEN, UUID.randomUUID().toString())
            editor.putString(USERNAME, username)
            editor.putFloat(BALANCE, 1000.0f)
            editor.apply()
            return true
        } else {
            return false
        }
    }

    override fun isLogged(): Boolean {
        return prefs.contains(AUTH_TOKEN)
    }

    override fun getUsername(): String {
        return prefs.getString(USERNAME, "")!!
    }

}