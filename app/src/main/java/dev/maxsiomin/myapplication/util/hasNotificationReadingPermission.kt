package dev.maxsiomin.myapplication.util

import android.content.Context
import android.provider.Settings

fun hasNotificationReadingPermission(context: Context): Boolean {
    // Example of such a string
    // dev.maxsiomin.myapplication/dev.maxsiomin.myapplication.service.MyNotificationListenerService:com.google.android.apps.nexuslauncher/com.android.launcher3.notification.NotificationListener
    val notificationListenerString =
        Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners") ?: ""
    return context.packageName in notificationListenerString
}
