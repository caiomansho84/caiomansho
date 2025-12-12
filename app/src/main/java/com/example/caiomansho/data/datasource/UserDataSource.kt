package com.example.caiomansho.data.datasource

import android.content.SharedPreferences
import com.example.caiomansho.data.repository.UserRepository
import com.example.caiomansho.util.AUTH_TOKEN
import com.example.caiomansho.util.BALANCE
import com.example.caiomansho.util.BASE_BALANCE
import com.example.caiomansho.util.CAIO_PASS
import com.example.caiomansho.util.CAIO_USERNAME
import com.example.caiomansho.util.USERNAME
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): UserRepository {
    override fun login(username: String, password: String): Boolean {
        if(username == CAIO_USERNAME && password == CAIO_PASS) {
            editor.putString(AUTH_TOKEN, UUID.randomUUID().toString())
            editor.putString(USERNAME, username)
            editor.putFloat(BALANCE, BASE_BALANCE)
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