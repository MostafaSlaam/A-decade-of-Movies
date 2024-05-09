package com.task.a_decade_of_movies.features.home.data.data_source

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.a_decade_of_movies.core.Constants
import com.task.a_decade_of_movies.core.ResponseDto
import com.task.a_decade_of_movies.core.readJSONFromAssets
import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO
import com.task.a_decade_of_movies.features.home.data.response.MoviesResponseDTO
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val context: Context
) : HomeRemoteDataSource {
    override suspend fun getMoviesFromFile(

    ): ResponseDto<List<MovieModelDTO>> {
        var movies:List<MovieModelDTO> = emptyList()
        try {
            val json = readJSONFromAssets(context, "movies.json")
            val typeToken = object : TypeToken<MoviesResponseDTO>() {}.type
            movies = Gson().fromJson<MoviesResponseDTO>(json, typeToken).movies
        }catch (e:Exception)
        {
            e.printStackTrace()
        }

        return ResponseDto<List<MovieModelDTO>>().apply {
            status=Constants.SUCCESS
            data=movies
        }
    }

}