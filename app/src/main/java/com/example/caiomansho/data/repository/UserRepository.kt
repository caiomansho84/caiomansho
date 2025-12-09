package com.example.caiomansho.data.repository

interface UserRepository {
    fun login(username: String, password: String): Boolean
    fun isLogged(): Boolean
    fun getUsername(): String

}