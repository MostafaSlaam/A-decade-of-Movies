package com.task.a_decade_of_movies.features.movie_details.data.data_source

import com.task.a_decade_of_movies.core.ResponseDto
import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO
import com.task.a_decade_of_movies.features.movie_details.data.response.ImagesResponseDTO

interface MovieRemoteDataSource{
    suspend fun getMoviesPhotos(
        text: String,
        page: Int,

    ): ImagesResponseDTO
}

