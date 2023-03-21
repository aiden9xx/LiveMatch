package com.aiden.soccer.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import domain.repositories.TeamDataRepository
import data.repositories.TeamDataSourceImpl
import data.repositories.MatchDataSourceImpl
import domain.repositories.MatchRepository

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ArticleRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindArticleRepository(repository: TeamDataSourceImpl): TeamDataRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindMatchRepository(repository: MatchDataSourceImpl): MatchRepository

}