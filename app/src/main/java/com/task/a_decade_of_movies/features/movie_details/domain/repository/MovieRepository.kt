package com.task.a_decade_of_movies.features.movie_details.domain.repository

import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.core.DataStatePaging
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotosModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMoviesPhotos(
        text: String,
        page: Int,

    ): Flow<DataStatePaging<PhotosModel>>

}