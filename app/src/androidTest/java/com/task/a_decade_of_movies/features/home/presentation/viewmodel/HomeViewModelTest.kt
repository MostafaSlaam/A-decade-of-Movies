package com.task.a_decade_of_movies.features.home.presentation.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.datasource.HomeRemoteDataSourceImplTest
import com.task.a_decade_of_movies.features.home.data.repository.HomeRepositoryImplTest
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import com.task.a_decade_of_movies.features.home.domain.usecase.GetMoviesFromFileUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeViewModelTest{
    lateinit var homeRemoteDataSource: HomeRemoteDataSource
    lateinit var homeRepository: HomeRepository
    lateinit var getMoviesFromFileUseCase: GetMoviesFromFileUseCase
    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        homeRemoteDataSource = HomeRemoteDataSourceImplTest().apply {
            context=appContext
        }
        homeRemoteDataSource
        homeRepository = HomeRepositoryImplTest(homeRemoteDataSource)
        getMoviesFromFileUseCase = GetMoviesFromFileUseCase(homeRepository)
    }


    //test read & parse data from file
    @Test
    fun getMoviesFromFileTest() = runTest {
        getMoviesFromFileUseCase.execute(Unit).collect {
            if (it is DataState.Success)
                Assert.assertTrue(it.data.isNotEmpty())
        }
    }
}