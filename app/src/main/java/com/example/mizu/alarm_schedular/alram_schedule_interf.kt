package com.example.mizu.alarm_schedular

import com.example.mizu.utils.water_reminder.WaterReminder

interface AlarmSchedularInterface {
    fun schedule(reminder: WaterReminder)
    fun cancel(reminder: WaterReminder)
}