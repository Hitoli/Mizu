package com.example.mizu

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.mizu.di.DIModule
import com.example.mizu.features.notifications.NotificationChannelService
import com.example.mizu.alarm_schedular.AlarmScheduler
import com.example.mizu.utils.water_reminder.WaterReminder
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.time.LocalDateTime

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
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
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
}