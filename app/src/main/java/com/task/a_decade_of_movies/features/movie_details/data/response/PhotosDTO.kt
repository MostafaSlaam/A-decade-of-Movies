package com.task.a_decade_of_movies.features.movie_details.data.response

class PhotosDTO(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<PhotoDTO>
)