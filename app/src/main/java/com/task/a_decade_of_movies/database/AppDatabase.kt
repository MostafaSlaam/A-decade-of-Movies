package com.task.a_decade_of_movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel


@Database(
    entities = [MovieModel::class], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDepDao(): MainDepDao
}