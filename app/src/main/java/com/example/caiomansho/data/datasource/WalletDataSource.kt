package com.example.caiomansho.data.datasource

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.RequiresPermission
import com.example.caiomansho.data.repository.WalletRepository
import com.example.caiomansho.util.BALANCE
import com.example.caiomansho.util.NotificationUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): WalletRepository {

    override suspend fun getBalance(): Float {
        return prefs.getFloat(BALANCE, 0.0f)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun transfer(value: Float) {
        delay(2000)
        editor.putFloat(BALANCE, getBalance() - value)
        editor.apply()
        NotificationUtil().sendNotification(context = context, value = value)
    }

}