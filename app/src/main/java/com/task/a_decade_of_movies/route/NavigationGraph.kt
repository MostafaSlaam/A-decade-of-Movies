package com.task.a_decade_of_movies.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.task.a_decade_of_movies.features.home.presentation.view.HomeScreen
import com.task.a_decade_of_movies.features.main.MainViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {


    NavHost(
        navController = navHostController,
        startDestination =
        AppScreen.HomeScreen.route,

        ) {

        composable(route = AppScreen.HomeScreen.route) {

            HomeScreen(navController = navHostController, mainViewModel = mainViewModel)


        }

    }
}