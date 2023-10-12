package dev.maxsiomin.myapplication.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.maxsiomin.myapplication.data.local.NotificationsDatabase
import dev.maxsiomin.myapplication.util.NotificationChecker
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
    @Singleton
    fun provideNotificationChecker(@ApplicationContext context: Context): NotificationChecker {
        return NotificationChecker(context)
    }

}