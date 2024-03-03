package dev.maxsiomin.myapplication.core.data.mapper

import dev.maxsiomin.myapplication.core.data.local.NotificationEntity
import dev.maxsiomin.myapplication.notifications.domain.model.SavedNotification

fun SavedNotification.toNotificationEntity(): NotificationEntity {
    return NotificationEntity(
        id = 0,
        app = app,
        time = time,
        title = title,
        text = text
    )
}

fun NotificationEntity.toSavedNotification(): SavedNotification {
    return SavedNotification(
        app = app,
        time = time,
        title = title,
        text = text
    )
}
