package com.task.a_decade_of_movies.features.home.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.a_decade_of_movies.core.Constants
import com.task.a_decade_of_movies.core.ResponseDto
import com.task.a_decade_of_movies.core.readJSONFromAssets
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO
import com.task.a_decade_of_movies.features.home.data.response.MoviesResponseDTO
import javax.inject.Inject

class HomeRemoteDataSourceImplTest(

) : HomeRemoteDataSource {
    lateinit var context: Context


    override suspend fun getMoviesFromFile(

    ): ResponseDto<List<MovieModelDTO>> {
        var movies:List<MovieModelDTO> = emptyList()
        try {
            val json = readJSONFromAssets(context, "test_movies.json")
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