package com.task.a_decade_of_movies.features.home.data.repository

import com.task.a_decade_of_movies.core.Constants
import com.task.a_decade_of_movies.core.ConstantsErrorHandler
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.core.handleError
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.mapper.mapFromListModel
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource) : HomeRepository {
    override suspend fun getMoviesFromFile(): Flow<DataState<List<MovieModel>>> = flow{
        emit(DataState.Loading)
        try {
            val response =

                homeRemoteDataSource.getMoviesFromFile()


            if (response.status == Constants.SUCCESS) {
                emit(DataState.Success(response.data!!.mapFromListModel()))
            } else {
                emit(DataState.Failure(response.message))
            }
        } catch (exception: Exception) {
            emit(DataState.Failure(uiText = handleError(ConstantsErrorHandler.EXCEPTION_MESSAGE)))
        }
    }
}