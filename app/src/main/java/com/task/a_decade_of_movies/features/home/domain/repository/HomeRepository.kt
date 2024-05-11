package com.task.a_decade_of_movies.features.home.domain.repository

import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getMoviesFromFile(): Flow<DataState<List<MovieModel>>>
    suspend fun categoriesSortingList(list:List<MovieModel>): Flow<DataState<List<MovieModel>>>

    suspend fun search(word:String,sortedList:List<MovieModel>): Flow<DataState<List<MovieModel>>>

    suspend fun getMoviesDB(): Flow<DataState<List<MovieModel>>>

    suspend fun insertMoviesDB(list:List<MovieModel>):Flow<DataState<Unit>>
}