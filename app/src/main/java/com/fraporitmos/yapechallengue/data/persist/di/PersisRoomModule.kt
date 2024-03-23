package com.fraporitmos.yapechallengue.data.persist.di

import android.app.Application
import com.fraporitmos.yapechallengue.data.persist.repository.ApplicationPersistRepository
import com.fraporitmos.yapechallengue.data.persist.repository.RoomPersistDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersisRoomModule {
    @Provides
    fun provideRepository(app: Application) =
        ApplicationPersistRepository(RoomPersistDataSource(app))
}