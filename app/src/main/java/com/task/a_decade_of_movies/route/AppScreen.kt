package com.task.a_decade_of_movies.route

sealed class AppScreen(val route: String) {
    object HomeScreen : AppScreen(ConstantAppScreenName.HOME_SCREEN)
    object MovieScreen : AppScreen(ConstantAppScreenName.MOVIE_SCREEN)

}

object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val MOVIE_SCREEN = "movie_screen"

}