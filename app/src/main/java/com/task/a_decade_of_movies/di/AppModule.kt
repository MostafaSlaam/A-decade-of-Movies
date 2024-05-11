package com.task.a_decade_of_movies.di

import android.content.Context
import com.task.a_decade_of_movies.core.Api
import com.task.a_decade_of_movies.database.LocalDataSource
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSourceImpl
import com.task.a_decade_of_movies.features.home.data.repository.HomeRepositoryImpl
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import com.task.a_decade_of_movies.features.movie_details.data.data_source.MovieRemoteDataSource
import com.task.a_decade_of_movies.features.movie_details.data.data_source.MoviesRemoteDataSourceImpl
import com.task.a_decade_of_movies.features.movie_details.data.repository.MovieRepositoryImpl
import com.task.a_decade_of_movies.features.movie_details.domain.repository.MovieRepository
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
        authRemoteDataSource: HomeRemoteDataSource,
        localDataSource: LocalDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(authRemoteDataSource,localDataSource)
    }


}


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(
        api: Api,
    ): MovieRemoteDataSource {
        return MoviesRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesHomeRepository(
        authRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(authRemoteDataSource)
    }


}