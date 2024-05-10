package com.task.a_decade_of_movies.features.movie_details.data.data_source

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.a_decade_of_movies.core.Api
import com.task.a_decade_of_movies.core.Constants
import com.task.a_decade_of_movies.core.ResponseDto
import com.task.a_decade_of_movies.core.readJSONFromAssets
import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO
import com.task.a_decade_of_movies.features.home.data.response.MoviesResponseDTO
import com.task.a_decade_of_movies.features.movie_details.data.response.ImagesResponseDTO
import retrofit2.http.Query
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : MovieRemoteDataSource {
    override suspend fun getMoviesPhotos(
      text: String,
         page: Int,

    ): ImagesResponseDTO{
        return api.getPhotos(
            text = text,
            page = page,

        )
    }

}