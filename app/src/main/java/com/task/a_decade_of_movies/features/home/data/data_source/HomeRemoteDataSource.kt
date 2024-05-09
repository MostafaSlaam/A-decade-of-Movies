package com.task.a_decade_of_movies.features.home.data.data_source

import com.task.a_decade_of_movies.core.ResponseDto
import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO

interface HomeRemoteDataSource{
    suspend fun getMoviesFromFile(

    ): ResponseDto<List<MovieModelDTO>>
}

