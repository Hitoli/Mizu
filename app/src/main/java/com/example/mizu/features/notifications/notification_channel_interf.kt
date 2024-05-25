package com.example.mizu.features.notifications

import com.example.mizu.utils.water_reminder.WaterReminder

interface NotificationChannelInterface {

    fun showNotification(reminder: String)
}