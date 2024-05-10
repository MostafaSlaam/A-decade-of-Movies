package com.task.a_decade_of_movies.features.home.domain.usecase

import com.task.a_decade_of_movies.core.BaseUseCase
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val repository: HomeRepository
) : BaseUseCase<Pair<String,List<MovieModel>>, Flow<DataState<List<MovieModel>>>> {
    override suspend fun execute(input: Pair<String,List<MovieModel>>): Flow<DataState<List<MovieModel>>> {
        return repository.search(input.first,input.second)
    }

}