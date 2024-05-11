package com.task.a_decade_of_movies.features.home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.usecase.GetInsertMoviesDBUseCase
import com.task.a_decade_of_movies.features.home.domain.usecase.GetMoviesFromDBUseCase
import com.task.a_decade_of_movies.features.home.domain.usecase.GetMoviesFromFileUseCase
import com.task.a_decade_of_movies.features.home.domain.usecase.GetMoviesSortedUseCase
import com.task.a_decade_of_movies.features.home.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesFromFileUseCase: GetMoviesFromFileUseCase,
    private val getMoviesSortedUseCase: GetMoviesSortedUseCase,
    private val getSearchUseCase: GetSearchUseCase,
    private val getInsertMoviesDBUseCase: GetInsertMoviesDBUseCase,
    private val getMoviesFromDBUseCase: GetMoviesFromDBUseCase
) : ViewModel() {

    private val _homeState = mutableStateOf<DataState<List<MovieModel>>>(DataState.Init)
    val homeState: State<DataState<List<MovieModel>>> = _homeState
    var search = mutableStateOf("")
    var unsortedMovies = emptyList<MovieModel>()
    var sortedMovies = emptyList<MovieModel>()
    private val _searchState = mutableStateOf<DataState<List<MovieModel>>>(DataState.Init)
    val searchState: State<DataState<List<MovieModel>>> = _searchState
    private var searchJob: Job = Job()

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                HomeEvent.GetMovies -> {
                    val job1 = async(Dispatchers.IO) {

                        getMoviesFromDB()
                    }
                    job1.await()
                    launch(Dispatchers.IO) { getSortedList(unsortedMovies) }

                }

                HomeEvent.Search -> {
                    if (searchJob.isActive)
                        searchJob.cancel()

                    searchJob = launch(Dispatchers.IO) { search() }
                }
            }
        }
    }


    private suspend fun getMoviesFromFile() {

        getMoviesFromFileUseCase.execute(
            Unit
        ).collect {
            _homeState.value = it
            if (it is DataState.Success) {
                insertMoviesDB(it.data)
                unsortedMovies = it.data
            }

        }
    }

    private suspend fun getMoviesFromDB(){
        getMoviesFromDBUseCase.execute(Unit).collect{
            if (it is DataState.Success && it.data.isEmpty())
                getMoviesFromFile()
            else{
                _homeState.value = it
                if (it is DataState.Success) {
                    unsortedMovies = it.data
                }
            }


        }
    }
    private suspend fun insertMoviesDB(movies:List<MovieModel>){
        getInsertMoviesDBUseCase.execute(movies).collect{

        }
    }

    private suspend fun getSortedList(list: List<MovieModel>) {
        getMoviesSortedUseCase.execute(list).collect {
            if (it is DataState.Success)
                sortedMovies = it.data
        }
    }

    private suspend fun search() {
        getSearchUseCase.execute(Pair(search.value, sortedMovies)).collect {
            _searchState.value = it
        }
    }
}


sealed class HomeEvent {

    object GetMovies : HomeEvent()
    object Search : HomeEvent()


}



