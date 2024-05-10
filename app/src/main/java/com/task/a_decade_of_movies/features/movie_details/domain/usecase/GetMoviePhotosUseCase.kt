package com.task.a_decade_of_movies.features.movie_details.domain.usecase

import com.task.a_decade_of_movies.core.BaseUseCase
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.core.DataStatePaging
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotosModel
import com.task.a_decade_of_movies.features.movie_details.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviePhotosUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Pair<String,Int>, Flow<DataStatePaging<PhotosModel>>> {
    override suspend fun execute(input: Pair<String,Int>): Flow<DataStatePaging<PhotosModel>> {
        return repository.getMoviesPhotos(text = input.first, page = input.second)
    }

}