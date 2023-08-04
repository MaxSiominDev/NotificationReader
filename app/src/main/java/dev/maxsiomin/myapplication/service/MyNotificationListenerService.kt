package dev.maxsiomin.myapplication.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.myapplication.data.local.NotificationEntity
import dev.maxsiomin.myapplication.domain.repository.NotificationsRepository
import dev.maxsiomin.myapplication.util.getApplicationByPackageName
import dev.maxsiomin.myapplication.util.mockUse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MyNotificationListenerService : NotificationListenerService() {

    @Inject
    lateinit var repo: NotificationsRepository

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate called")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
        val notification = sbn?.notification ?: return
        val extras = notification.extras ?: return
        if (extras.containsKey(Notification.EXTRA_TEXT).not()) return
        val text = extras.getString(Notification.EXTRA_TEXT) ?: "text = null"
        val title = extras.getString(Notification.EXTRA_TITLE) ?: "title = null"

        val pkg = sbn.packageName ?: ""

        val entity = NotificationEntity(
            id = 0,
            app = getApplicationByPackageName(pkg),
            text = text,
            title = title,
            time = System.currentTimeMillis(),
        )

        CoroutineScope(Dispatchers.IO).launch {
            repo.insertNotification(entity)
        }

        mockUse(text, title)
    }

    override fun onNotificationRemoved(
        sbn: StatusBarNotification?,
        rankingMap: RankingMap?,
        reason: Int
    ) {
        super.onNotificationRemoved(sbn, rankingMap, reason)
        if (reason == NOTIFICATION_DELETED_BY_TELEGRAM && isTelegramPackage(sbn?.packageName ?: "")) {
            showNotificationThatMessageWasDeletedInTelegram()
        }
    }

    private fun showNotificationThatMessageWasDeletedInTelegram() {

    }

    companion object {
        const val NOTIFICATION_DELETED_BY_TELEGRAM = 8

        fun isTelegramPackage(pkg: String): Boolean {
            val telegramApk = "org.telegram.messenger.web"
            val telegramPlayStore = "org.telegram.messenger"
            return pkg in listOf(telegramApk, telegramPlayStore)
        }
    }
}