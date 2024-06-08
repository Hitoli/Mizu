package com.example.mizu.features.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.mizu.MainActivity
import com.example.mizu.R
import com.example.mizu.utils.water_reminder.WaterReminder

class NotificationChannelService(
    private val context:Context
):NotificationChannelInterface {

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager

    companion object{
         val notificationIDWaterReminder ="Water_reminder_notification_ID"
    }

    override fun showNotification(reminder: String) {
        var activityIntent = Intent(context,MainActivity::class.java)
        var activityPendingIntent = PendingIntent.getActivity(
            context,
            1234,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val reminderNotification = NotificationCompat.Builder(context, notificationIDWaterReminder).setSmallIcon(
            R.drawable.mizulogo
        ).setContentTitle("Water Reminder !").setContentText(reminder).setContentIntent(activityPendingIntent).setAutoCancel(false)
            .setOngoing(true).build()

        notificationManager.notify(1234,reminderNotification)
    }


}