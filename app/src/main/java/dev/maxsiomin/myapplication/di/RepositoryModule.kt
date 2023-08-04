package dev.maxsiomin.myapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.maxsiomin.myapplication.data.repository.NotificationsRepositoryImpl
import dev.maxsiomin.myapplication.domain.repository.NotificationsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        repositoryImpl: NotificationsRepositoryImpl
    ): NotificationsRepository

}
