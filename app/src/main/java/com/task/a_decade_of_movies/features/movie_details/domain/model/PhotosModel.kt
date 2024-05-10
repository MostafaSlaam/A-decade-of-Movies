package com.task.a_decade_of_movies.features.movie_details.domain.model

class PhotosModel(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<PhotoModel>
)