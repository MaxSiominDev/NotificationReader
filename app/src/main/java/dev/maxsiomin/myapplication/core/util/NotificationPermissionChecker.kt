package dev.maxsiomin.myapplication.core.util

import android.content.Context
import android.provider.Settings
import javax.inject.Inject

class NotificationPermissionChecker @Inject constructor(private val context: Context) {

    fun hasNotificationReadingPermission(): Boolean {
        // Example of such a string
        // dev.maxsiomin.myapplication/dev.maxsiomin.myapplication.service.MyNotificationListenerService:com.google.android.apps.nexuslauncher/com.android.launcher3.notification.NotificationListener
        val notificationListenerString =
            Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners") ?: ""
        return context.packageName in notificationListenerString
    }

}
