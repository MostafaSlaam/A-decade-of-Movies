package com.edu.academy.features.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.academy.core.app.AppPreferences
import com.edu.academy.core.app.Constants
import com.edu.academy.core.theme.AppTheme

import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(MainState())



    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.ThemeChange -> {
                    formState = formState.copy(theme = event.theme)
                    AppPreferences.setTheme( event.theme.name)
                }

                is MainEvent.VisibleBottomNavigationChange -> {
                    formState =
                        formState.copy(isBottomNavigationVisible = event.isBottomNavigationVisible)
                    formState = formState.copy(currentStatusBar = event.currentStatusBar)
                }

                is MainEvent.IndexBottomNavigationChange -> {
                    formState =
                        formState.copy(currentIndexBottomNavigation = event.currentIndexBottomNavigation)
                }

            }
        }
    }

}

sealed class MainEvent {

    data class ThemeChange(val theme: AppTheme) : MainEvent()
    data class VisibleBottomNavigationChange(val isBottomNavigationVisible: Boolean, val currentStatusBar: Int = 0) : MainEvent()
    data class IndexBottomNavigationChange(val currentIndexBottomNavigation: Int) : MainEvent()
}

data class MainState(
    val theme: AppTheme = AppTheme.valueOf(AppPreferences.getTheme()),
    val isBottomNavigationVisible: Boolean = true,
    val currentIndexBottomNavigation: Int = Constants.HIDE_BOTTOM_NAVIGATION,
    val currentStatusBar: Int = 0,
)