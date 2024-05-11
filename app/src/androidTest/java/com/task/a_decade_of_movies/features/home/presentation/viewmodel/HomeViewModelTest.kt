package com.task.a_decade_of_movies.features.home.presentation.viewmodel

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat

import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.database.AppDatabase
import com.task.a_decade_of_movies.database.MainDepDao
import com.task.a_decade_of_movies.features.home.data.data_source.HomeRemoteDataSource
import com.task.a_decade_of_movies.features.home.data.datasource.HomeRemoteDataSourceImplTest
import com.task.a_decade_of_movies.features.home.data.repository.HomeRepositoryImplTest
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.repository.HomeRepository
import com.task.a_decade_of_movies.features.home.domain.usecase.GetMoviesFromFileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch


@RunWith(AndroidJUnit4::class)
@SmallTest
class HomeViewModelTest {
    lateinit var homeRemoteDataSource: HomeRemoteDataSource
    lateinit var homeRepository: HomeRepository
    lateinit var getMoviesFromFileUseCase: GetMoviesFromFileUseCase

    //test database
    private lateinit var database: AppDatabase
    private lateinit var wordsDao: MainDepDao

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        homeRemoteDataSource = HomeRemoteDataSourceImplTest().apply {
            context = appContext
        }
        homeRemoteDataSource
        homeRepository = HomeRepositoryImplTest(homeRemoteDataSource)
        getMoviesFromFileUseCase = GetMoviesFromFileUseCase(homeRepository)
    }

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        wordsDao = database.mainDepDao()
    }


    //test read & parse data from file
    @Test
    fun getMoviesFromFileTest() = runTest {
        getMoviesFromFileUseCase.execute(Unit).collect {
            if (it is DataState.Success)
                Assert.assertTrue(it.data.isNotEmpty())
        }
    }


    @Test
    fun insertMovie_returnsTrue() = runTest {
        val movie = MovieModel(title = "Hello", 2024, emptyList<String>(), emptyList(), 5)
        wordsDao.insertDeps(movie)
        val movies = wordsDao.getAllMovies()
        assertThat(movies.contains(movie)).isTrue()

    }
    @Test
    fun test_deleteTable() = runTest {
        val movie = MovieModel(title = "Hello", 2024, emptyList<String>(), emptyList(), 5)
        wordsDao.insertDeps(movie)
        wordsDao.nukeMoviesTable()
        val movies = wordsDao.getAllMovies()
        assertThat(movies.isEmpty()).isTrue()
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}