package com.task.a_decade_of_movies.di

import android.content.Context
import androidx.room.Room
import com.task.a_decade_of_movies.database.AppDatabase
import com.task.a_decade_of_movies.database.MainDepDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "movies.db"
        )
//            .fallbackToDestructiveMigration()
            .build()
    }



    @Provides
    fun provideLogDao(database: AppDatabase): MainDepDao {
        return database.mainDepDao()
    }
}