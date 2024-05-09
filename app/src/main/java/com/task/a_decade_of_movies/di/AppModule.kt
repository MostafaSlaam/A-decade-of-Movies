package com.task.a_decade_of_movies.di

import android.content.Context
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSourceImpl
import com.task.a_decade_of_movies.features.home.data.repository.HomeRepositoryImpl
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Singleton
    @Provides
    fun providesHomeRemoteDataSource(
        context: Context,
    ): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(context)
    }

    @Singleton
    @Provides
    fun providesHomeRepository(
        authRemoteDataSource: HomeRemoteDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(authRemoteDataSource)
    }


}