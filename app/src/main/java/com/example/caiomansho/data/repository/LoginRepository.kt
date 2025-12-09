package com.example.caiomansho.data.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Boolean
}