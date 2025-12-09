package com.example.caiomansho.data.datasource

import android.content.Context
import com.example.caiomansho.data.repository.LoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context
): LoginRepository {
    override fun login(username: String, password: String): Boolean {
        return username == "caio" && password == "1234"
    }
}