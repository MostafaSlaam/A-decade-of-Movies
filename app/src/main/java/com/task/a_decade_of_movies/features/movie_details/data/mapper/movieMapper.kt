package com.task.a_decade_of_movies.features.movie_details.data.mapper

import com.task.a_decade_of_movies.features.home.data.response.MovieModelDTO
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel
import com.task.a_decade_of_movies.features.movie_details.data.response.PhotoDTO
import com.task.a_decade_of_movies.features.movie_details.data.response.PhotosDTO
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotoModel
import com.task.a_decade_of_movies.features.movie_details.domain.model.PhotosModel

fun PhotosDTO.mapFromModel() = PhotosModel(
    page = this.page,
    perpage = this.perpage,
    total = this.total,
    pages = this.pages,
    photo = this.photo.mapFromListModel()

)

fun PhotoDTO.mapFromModel() = PhotoModel(
    id = this.id, secret = this.secret, server = this.server, title = this.title, farm = this.farm

)

fun List<PhotoDTO>.mapFromListModel(): List<PhotoModel> {
    return this.map {
        it.mapFromModel()
    }
}