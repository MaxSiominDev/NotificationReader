package dev.maxsiomin.myapplication.domain.repository

import dev.maxsiomin.myapplication.data.local.NotificationEntity
import dev.maxsiomin.myapplication.domain.model.SavedNotification

interface NotificationsRepository {

    suspend fun insertNotification(entity: NotificationEntity)

    suspend fun loadAllNotifications(): List<SavedNotification>

}