package com.task.a_decade_of_movies.features.home.data.repository

import android.util.ArrayMap
import androidx.compose.ui.text.toLowerCase
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
import java.util.Locale
import javax.inject.Inject

class HomeRepositoryImplTest(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun getMoviesFromFile(): Flow<DataState<List<MovieModel>>> = flow {
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

    override suspend fun categoriesSortingList(list: List<MovieModel>): Flow<DataState<List<MovieModel>>> =
        flow {
            emit(DataState.Loading)
            var sorted =
                list.sortedWith(compareByDescending { it.rating })
//            var mapList = HashMap<Int, ArrayList<MovieModel>>()
            try {


                //comment if need to view sorted movies
//                sorted.forEach { movieModel ->
//                    if (mapList.contains(movieModel.year))
//                        mapList[movieModel.year]?.apply {
//                            add(movieModel)
//                        }
//                    else {
//                        mapList[movieModel.year] = ArrayList<MovieModel>().apply {
//                            add(movieModel)
//                        }
//
//                    }
//                }
//                var finalList = ArrayList<MovieModel>()
//                mapList.values.forEach { value ->
//                    finalList.addAll(ArrayList(value.take(5)))
//                }
//
//                emit(DataState.Success<List<MovieModel>>(finalList))
//                var newMap =
//                    mapList.map { it.key to it.value }.sortedByDescending { it.first }.toMap()
//                emit(DataState.Success<List<MovieModel>>(newMap.values.flatten()))
                emit(DataState.Success(sorted))

            } catch (exception: Exception) {
                emit(DataState.Failure(uiText = handleError(ConstantsErrorHandler.EXCEPTION_MESSAGE)))
            }
        }

    override suspend fun search(
        word: String,
        sortedList: List<MovieModel>
    ): Flow<DataState<List<MovieModel>>> =
        flow {
            emit(DataState.Loading)

            var mapList = HashMap<Int, ArrayList<MovieModel>>()
            try {

                //search and save year with related movies
                sortedList.forEach { movieModel ->
                    if (movieModel.title.lowercase().contains(word.lowercase())) {
                        if (mapList.contains(movieModel.year))
                            mapList[movieModel.year]?.apply {
                                add(movieModel)
                            }
                        else {
                            mapList[movieModel.year] = ArrayList<MovieModel>().apply {
                                add(movieModel)
                            }

                        }
                    }

                }
                //sorted years
                var newMap =
                    mapList.map { it.key to it.value }.sortedByDescending { it.first }.toMap()
                var finalList = ArrayList<MovieModel>()
                //take top 5 movies
                newMap.values.forEach { value ->
                    finalList.addAll(ArrayList(value.take(5)))
                }

                emit(DataState.Success<List<MovieModel>>(finalList))


            } catch (exception: Exception) {
                emit(DataState.Failure(uiText = handleError(ConstantsErrorHandler.EXCEPTION_MESSAGE)))
            }
        }
}