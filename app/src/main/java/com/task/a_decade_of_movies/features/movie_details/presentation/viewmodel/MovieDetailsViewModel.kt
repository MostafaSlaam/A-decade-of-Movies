package com.task.a_decade_of_movies.features.movie_details.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.a_decade_of_movies.core.AppPreferences
import com.task.a_decade_of_movies.core.DataState
import com.task.a_decade_of_movies.core.DataStatePaging
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotoModel
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotosModel
import com.task.a_decade_of_movies.features.movie_details.domain.usecase.GetMoviePhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMoviePhotosUseCase: GetMoviePhotosUseCase
) : ViewModel() {

    private val _movieImagesState =
        mutableStateOf<DataStatePaging<PhotosModel>>(DataStatePaging.Init)
    val movieImagesState: State<DataStatePaging<PhotosModel>> = _movieImagesState
    var images = mutableStateListOf<PhotoModel>()
    var formState by mutableStateOf(MovieState())

    init {
        formState = formState.copy(currentMovie = AppPreferences.getCurrentMovie())


    }

    fun onEvent(event: MovieEvent) {
        viewModelScope.launch {
            when (event) {
                MovieEvent.LoadImages -> {
                    formState = formState.copy(page = formState.page + 1)
                    getMoviesFromFile()
                }


            }
        }
    }


    private suspend fun getMoviesFromFile() {

        getMoviePhotosUseCase.execute(
            Pair(formState.currentMovie?.title ?: "", formState.page)
        ).collect {
            _movieImagesState.value = it
            if (it is DataStatePaging.Success) {
                if (it.data.photo.isNotEmpty())

                    images.addAll(it.data.photo)
                else
                    formState = formState.copy(isNext = false)
            } else if (it is DataStatePaging.Failure)
                formState = formState.copy(isNext = false)

        }
    }


}


sealed class MovieEvent {

    object LoadImages : MovieEvent()


}

data class MovieState(
    val currentMovie: MovieModel? = null,
    val page: Int = 0,
    var isNext: Boolean = true
)



