package com.task.a_decade_of_movies.features.home.domain.repository

import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getMoviesFromFile(): Flow<DataState<List<MovieModel>>>
}