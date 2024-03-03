package dev.maxsiomin.myapplication.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.maxsiomin.myapplication.core.data.local.NotificationsDao
import dev.maxsiomin.myapplication.core.data.local.NotificationsDatabase
import dev.maxsiomin.myapplication.core.data.repository.NotificationsRepositoryImpl
import dev.maxsiomin.myapplication.notifications.domain.repository.NotificationsRepository
import dev.maxsiomin.myapplication.core.util.NotificationChecker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotificationsDatabase {
        return Room.databaseBuilder(
            context,
            NotificationsDatabase::class.java,
            "Notifications DB"
        ).build()
    }

    @Provides
    fun provideDao(db: NotificationsDatabase): NotificationsDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideNotificationChecker(@ApplicationContext context: Context): NotificationChecker {
        return NotificationChecker(context)
    }

    @Provides
    fun provideStockRepository(
        dao: NotificationsDao,
    ): NotificationsRepository {
        return NotificationsRepositoryImpl(dao = dao)
    }

}