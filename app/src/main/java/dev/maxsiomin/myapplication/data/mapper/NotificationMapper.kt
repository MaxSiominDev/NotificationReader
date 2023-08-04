package dev.maxsiomin.myapplication.data.mapper

import dev.maxsiomin.myapplication.data.local.NotificationEntity
import dev.maxsiomin.myapplication.domain.model.SavedNotification

fun SavedNotification.toNotificationEntity(): NotificationEntity {
    return NotificationEntity(
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
