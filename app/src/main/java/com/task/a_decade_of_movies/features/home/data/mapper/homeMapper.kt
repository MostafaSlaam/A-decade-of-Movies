package com.task.a_decade_of_movies.features.home.data.mapper

import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel

fun MovieModelDTO.mapFromModel() = MovieModel(
    title = this.title,
    year = this.year,
    cast = this.cast,
    genres = this.genres,
    rating = this.rating
)

fun List<MovieModelDTO>.mapFromListModel(): List<MovieModel> {
    return this.map {
        it.mapFromModel()
    }
}