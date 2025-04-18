package com.example.mizu

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.mizu.di.DIModule
import com.example.mizu.features.notifications.NotificationChannelService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseAppMizu:Application(){
    override fun onCreate() {

        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseAppMizu)
            modules(DIModule)
        }
        createNotificationChannel()



    }





    // Creates a Notification Channel to Show Notifications
    private fun createNotificationChannel(){
        val channel = NotificationChannel(
            NotificationChannelService.notificationIDWaterReminder,
            "Water Reminders",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description =" Reminds user to drink water"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}