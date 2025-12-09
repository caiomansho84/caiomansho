package com.example.caiomansho.data.repository

interface WalletRepository {
    fun getBalance(): Float
    fun transfer(value: Float)
}