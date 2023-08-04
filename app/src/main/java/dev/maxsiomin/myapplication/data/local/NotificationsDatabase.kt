package dev.maxsiomin.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NotificationEntity::class],
    version = 1
)
abstract class NotificationsDatabase : RoomDatabase() {
    abstract val dao: NotificationsDao
}