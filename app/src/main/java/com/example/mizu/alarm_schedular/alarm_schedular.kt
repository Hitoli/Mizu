package com.example.mizu.alarm_schedular

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.example.mizu.receiver.AlarmReceiver
import com.example.mizu.utils.water_reminder.WaterReminder
import java.time.ZoneId

class AlarmScheduler(
    private val context:Context
): AlarmSchedularInterface {

    var alarmManager =context.getSystemService(AlarmManager::class.java)

    override fun schedule(reminder: WaterReminder) {
        var intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("waterReminderMessage",reminder.message)
        }
        val triggerAtMillis = SystemClock.elapsedRealtime() + 1 * 60 * 60 * 1000
        val intervalMillis = 1 * 60 * 60 * 1000
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            intervalMillis.toLong(),
            PendingIntent.getBroadcast(
                context,
                reminder.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(reminder: WaterReminder) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                reminder.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}