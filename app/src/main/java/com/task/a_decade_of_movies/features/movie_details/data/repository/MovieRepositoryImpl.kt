package com.task.a_decade_of_movies.features.movie_details.data.repository

import android.util.ArrayMap
import androidx.compose.ui.text.toLowerCase
import com.task.a_decade_of_movies.core.Constants
import com.task.a_decade_of_movies.core.ConstantsErrorHandler
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.core.DataStatePaging
import com.task.a_decade_of_movies.core.handleError
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.mapper.mapFromListModel
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import com.task.a_decade_of_movies.features.movie_details.data.data_source.MovieRemoteDataSource
import com.task.a_decade_of_movies.features.movie_details.data.mapper.mapFromModel
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotosModel
import com.task.a_decade_of_movies.features.movie_details.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMoviesPhotos(
        text: String,
        page: Int): Flow<DataStatePaging<PhotosModel>> = flow {
        if (page == 1)
            emit(DataStatePaging.Loading)
        else
            emit(DataStatePaging.NextLoading)
        try {
            val response =

                movieRemoteDataSource.getMoviesPhotos(text = text, page = page)


            if (response.stat =="ok") {
                emit(DataStatePaging.Success(response.photos.mapFromModel()))
            } else {
                emit(DataStatePaging.Failure("error"))
            }
        } catch (exception: Exception) {
            emit(DataStatePaging.Failure(uiText = handleError(ConstantsErrorHandler.EXCEPTION_MESSAGE)))
        }
    }

}