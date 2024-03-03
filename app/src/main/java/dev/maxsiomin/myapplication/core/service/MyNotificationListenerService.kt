package dev.maxsiomin.myapplication.core.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.myapplication.core.data.local.NotificationEntity
import dev.maxsiomin.myapplication.notifications.domain.repository.NotificationsRepository
import dev.maxsiomin.myapplication.core.util.getApplicationByPackageName
import dev.maxsiomin.myapplication.core.util.mockUse
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

        if (pkg in ignorePackages) return

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

    companion object {
        val ignorePackages = listOf(
            "ru.yandex.music",
            "android",
            "com.coloros.alarmclock",
            "com.yandex.browser",
        )

        fun isTelegramPackage(pkg: String): Boolean {
            val telegramApk = "org.telegram.messenger.web"
            val telegramPlayStore = "org.telegram.messenger"
            return pkg in listOf(telegramApk, telegramPlayStore)
        }
    }
}