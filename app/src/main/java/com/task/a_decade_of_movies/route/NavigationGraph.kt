package com.task.a_decade_of_movies.route

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edu.academy.features.aboutus.view.AboutUsScreen
import com.edu.academy.features.courseDetails.presentation.view.CourseDetailsScreen
import com.edu.academy.features.courses.presentation.view.CoursesScreen
import com.edu.academy.features.home.presentation.view.HomeScreen
import com.edu.academy.features.main.MainViewModel
import com.edu.academy.features.packageCourses.presentation.view.PackageCoursesScreen
import com.edu.academy.features.packages.presentation.view.PackagesScreen
import com.edu.academy.features.various.presentation.view.VariousScreen
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
        composable(route = AppScreen.MyCoursesScreen.route) {
            CoursesScreen(navController = navHostController, mainViewModel = mainViewModel)
        }
        composable(route = AppScreen.PackagesScreen.route) {
            PackagesScreen(navController = navHostController, mainViewModel = mainViewModel)
        }
        composable(route = AppScreen.VariousScreen.route) {
            VariousScreen(navController = navHostController, mainViewModel = mainViewModel)
        }
        composable(AppScreen.AboutUsScreen.route){
            AboutUsScreen(navHostController,mainViewModel)
        }
        composable(AppScreen.PackageCoursesScreen.route+"/{id}"){
            val id = remember {
                it.arguments?.getString("id")
            }
            PackageCoursesScreen(navController = navHostController, mainViewModel = mainViewModel, id = (id!!.toInt()))
        }
        composable(AppScreen.CourseDetailsScreen.route+"/{id}"){
            val id = remember {
                it.arguments?.getString("id")
            }
            CourseDetailsScreen(navController = navHostController, mainViewModel = mainViewModel, id = (id!!.toInt()))
        }
    }
}