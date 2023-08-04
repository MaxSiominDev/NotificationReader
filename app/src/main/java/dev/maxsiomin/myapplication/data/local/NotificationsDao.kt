package dev.maxsiomin.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationsDao {

    @Insert
    suspend fun insertNotification(entity: NotificationEntity)

    @Query(value = "SELECT * FROM notifications")
    suspend fun loadAllNotifications(): List<NotificationEntity>

}