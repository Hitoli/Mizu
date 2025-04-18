package com.example.mizu.model.water_reminder

import java.time.LocalDateTime

data class WaterReminder(
    val time:LocalDateTime,
    val message:String
)
