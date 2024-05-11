package com.task.a_decade_of_movies.database

import androidx.annotation.WorkerThread
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mainDepDao: MainDepDao) {


    suspend fun insertMovies(vararg items: MovieModel) {
        mainDepDao.insertDeps(*items)
    }


    suspend fun removeMovies() {
        mainDepDao.nukeMoviesTable()
    }


    suspend fun getAllMovies(): List<MovieModel> {
        return mainDepDao.getAllMovies()
    }
}