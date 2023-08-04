package dev.maxsiomin.myapplication.data.repository

import dev.maxsiomin.myapplication.data.local.NotificationEntity
import dev.maxsiomin.myapplication.data.local.NotificationsDao
import dev.maxsiomin.myapplication.data.local.NotificationsDatabase
import dev.maxsiomin.myapplication.data.mapper.toSavedNotification
import dev.maxsiomin.myapplication.domain.model.SavedNotification
import dev.maxsiomin.myapplication.domain.repository.NotificationsRepository
import dev.maxsiomin.myapplication.util.mockUse
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val db: NotificationsDatabase,
) : NotificationsRepository {

    override suspend fun insertNotification(entity: NotificationEntity) {
        db.dao.insertNotification(entity)
    }

    override suspend fun loadAllNotifications(): List<SavedNotification> {
        return db.dao.loadAllNotifications().map {
            it.toSavedNotification()
        }.sortedBy {
            it.time
        }.reversed()
    }
}