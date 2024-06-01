package com.example.mizu.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.mizu.features.notifications.NotificationChannelService

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val reminder = intent?.getStringExtra("waterReminderMessage")?:return
        val reminderNotification = NotificationChannelService(context = context)
        reminderNotification.showNotification(reminder)
    }
}