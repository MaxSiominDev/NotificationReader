package dev.maxsiomin.myapplication.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationsDao {

    @Insert
    suspend fun insertNotification(entity: NotificationEntity)

    @Query(value = "SELECT * FROM notifications ORDER BY ID DESC LIMIT 10000")
    suspend fun loadAllNotifications(): List<NotificationEntity>

}