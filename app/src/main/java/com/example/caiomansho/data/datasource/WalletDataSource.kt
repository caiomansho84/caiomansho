package com.example.caiomansho.data.datasource

import android.content.SharedPreferences
import com.example.caiomansho.data.repository.WalletRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletDataSource @Inject constructor(
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): WalletRepository {

    override fun getBalance(): Float {
        return prefs.getFloat("balance", 0.0f)
    }

    override fun transfer(value: Float) {
        editor.putFloat("balance", getBalance() - value)
    }

}