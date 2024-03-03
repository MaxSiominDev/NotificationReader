package dev.maxsiomin.myapplication.notifications.domain.repository

import dev.maxsiomin.myapplication.core.data.local.NotificationEntity
import dev.maxsiomin.myapplication.notifications.domain.model.SavedNotification

interface NotificationsRepository {

    suspend fun insertNotification(entity: NotificationEntity)

    suspend fun loadAllNotifications(): List<SavedNotification>

}