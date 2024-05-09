package com.task.a_decade_of_movies.features.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(MainState())



    fun onEvent(event: MainEvent) {
        viewModelScope.launch {

        }
    }

}

sealed class MainEvent {

}

data class MainState(
    val currentStatusBar: Int = 0,
)