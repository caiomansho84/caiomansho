package com.example.caiomansho.data.datasource

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.RequiresPermission
import com.example.caiomansho.data.repository.WalletRepository
import com.example.caiomansho.util.NotificationUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
): WalletRepository {

    override fun getBalance(): Float {
        return prefs.getFloat("balance", 0.0f)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun transfer(value: Float) {
        editor.putFloat("balance", getBalance() - value)
        editor.apply()
        NotificationUtil().sendNotification(context = context, value = value)
    }

}