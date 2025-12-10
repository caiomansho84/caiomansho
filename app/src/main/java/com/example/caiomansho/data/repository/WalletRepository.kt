package com.example.caiomansho.data.repository

interface WalletRepository {
    suspend fun getBalance(): Float
    suspend fun transfer(value: Float)
}