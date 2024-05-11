package com.task.a_decade_of_movies.features.home.presentation.viewmodel

import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.datasource.HomeRemoteDataSourceImplTest
import com.task.a_decade_of_movies.features.home.data.repository.HomeRepositoryImplTest
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import com.task.a_decade_of_movies.features.home.domain.usecase.GetSearchUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class HomeViewModelTest {
    lateinit var homeRemoteDataSource: HomeRemoteDataSource
    lateinit var homeRepository: HomeRepository
    lateinit var getSearchUseCase: GetSearchUseCase
    lateinit var movies: List<MovieModel>

    @Before
    fun setup() {
        homeRemoteDataSource = HomeRemoteDataSourceImplTest()
        homeRepository = HomeRepositoryImplTest(homeRemoteDataSource)
        getSearchUseCase = GetSearchUseCase(homeRepository)
        movies = listOf<MovieModel>(
            MovieModel("(500) Days of Summer", 2009, emptyList(), emptyList(), 5),
            MovieModel("12 Rounds", 2009, emptyList(), emptyList(), 5),
            MovieModel("17 Again", 2009, emptyList(), emptyList(), 5),
            MovieModel("2012", 2009, emptyList(), emptyList(), 5),
            MovieModel("Woody Harrelson", 2009, emptyList(), emptyList(), 5),
            MovieModel("Adam", 2009, emptyList(), emptyList(), 5)
        )
    }

    @Test
    fun testSearchFunctionality() = runTest {
        //test lowercase
        getSearchUseCase.execute(Pair("ada", movies)).collect {
            if (it is DataState.Success)
                Assert.assertEquals(true, it.data.isNotEmpty())
        }
        //test uppercase
        getSearchUseCase.execute(Pair("Ada", movies)).collect {
            if (it is DataState.Success)
                Assert.assertEquals(true, it.data.isNotEmpty())
        }
        //test word not found
        getSearchUseCase.execute(Pair("hello", movies)).collect {
            if (it is DataState.Success)
                Assert.assertEquals(false, it.data.isNotEmpty())
        }
    }


}