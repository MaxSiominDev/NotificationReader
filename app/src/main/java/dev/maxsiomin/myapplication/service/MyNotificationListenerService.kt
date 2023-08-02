package dev.maxsiomin.myapplication.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MyNotificationListenerService : NotificationListenerService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
        val notification = sbn?.notification ?: return
        val extras = notification.extras ?: return
        if (extras.containsKey(NOTIFICATION_TEXT).not()) return
        val text = extras.getString(NOTIFICATION_TEXT)


    }

    override fun onNotificationRemoved(
        sbn: StatusBarNotification?,
        rankingMap: RankingMap?,
        reason: Int
    ) {
        super.onNotificationRemoved(sbn, rankingMap, reason)
        if (reason == NOTIFICATION_DELETED_BY_TELEGRAM && isTelegramPackage(sbn?.packageName ?: "pck name empty")) {
            showNotificationThatMessageWasDeletedInTelegram()
        }
    }

    private fun showNotificationThatMessageWasDeletedInTelegram() {

    }

    companion object {
        const val NOTIFICATION_TEXT = "android.text"
        const val NOTIFICATION_DELETED_BY_TELEGRAM = 8

        fun isTelegramPackage(pck: String): Boolean {
            val telegramApk = "org.telegram.messenger.web"
            val telegramPlayStore = "org.telegram.messenger"
            return pck in listOf(telegramApk, telegramPlayStore)
        }
    }
}