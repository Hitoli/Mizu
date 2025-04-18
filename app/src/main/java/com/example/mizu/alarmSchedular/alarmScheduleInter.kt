package com.example.mizu.alarmSchedular

import com.example.mizu.model.water_reminder.WaterReminder

interface AlarmSchedularInterface {
    fun schedule(reminder: WaterReminder)
    fun cancel(reminder: WaterReminder)
}