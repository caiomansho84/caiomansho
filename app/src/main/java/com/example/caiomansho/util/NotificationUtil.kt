package com.example.caiomansho.util

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.caiomansho.MainActivity
import com.example.caiomansho.R

class NotificationUtil {
    private lateinit var contentIntent: PendingIntent

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun sendNotification(context: Context, value: Float) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, "default_channel")
            .setContentTitle("Nova transferência")
            .setContentText("Você enviou ${value}!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
            .build()

        val manager = NotificationManagerCompat.from(context)
        manager.notify(1001, notification)
    }

}