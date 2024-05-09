package com.task.a_decade_of_movies.features.home.data.response

data class MovieModelDTO(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
)