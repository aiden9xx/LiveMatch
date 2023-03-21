package com.aiden.soccer.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.local.TeamDatabase
import javax.inject.Singleton

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(application: Application) = TeamDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideArticleDao(database: TeamDatabase) = database.getArticleDao()
}
