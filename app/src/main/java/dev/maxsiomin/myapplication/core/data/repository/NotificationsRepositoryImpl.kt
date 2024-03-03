package dev.maxsiomin.myapplication.core.data.repository

import dev.maxsiomin.myapplication.core.data.local.NotificationEntity
import dev.maxsiomin.myapplication.core.data.local.NotificationsDao
import dev.maxsiomin.myapplication.core.data.local.NotificationsDatabase
import dev.maxsiomin.myapplication.core.data.mapper.toSavedNotification
import dev.maxsiomin.myapplication.notifications.domain.model.SavedNotification
import dev.maxsiomin.myapplication.notifications.domain.repository.NotificationsRepository
import dev.maxsiomin.myapplication.core.util.mockUse
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val dao: NotificationsDao,
) : NotificationsRepository {

    override suspend fun insertNotification(entity: NotificationEntity) {
        dao.insertNotification(entity)
    }

    override suspend fun loadAllNotifications(): List<SavedNotification> {
        return dao.loadAllNotifications().map {
            it.toSavedNotification()
        }.sortedBy {
            it.time
        }.reversed()
    }
}