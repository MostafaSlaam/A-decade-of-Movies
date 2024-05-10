package com.task.a_decade_of_movies.features.home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.home.domain.usecase.GetMoviesFromFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesFromFileUseCase: GetMoviesFromFileUseCase
) : ViewModel() {

//    var formState by mutableStateOf(HomeState())


    private val _homeState = mutableStateOf<DataState<List<MovieModel>>>(DataState.Init)
    val homeState: State<DataState<List<MovieModel>>> = _homeState
    var search = mutableStateOf("")


    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                HomeEvent.GetDataFromFile -> {
                    launch(Dispatchers.IO) { getMoviesFromFile() }

                }
            }
        }
    }


    private suspend fun getMoviesFromFile() {
        getMoviesFromFileUseCase.execute(
            Unit
        ).collect {
            _homeState.value = it


        }
    }
}


sealed class HomeEvent {

    object GetDataFromFile : HomeEvent()


}



