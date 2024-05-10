package com.task.a_decade_of_movies.core

import com.task.a_decade_of_movies.features.movie_details.data.response.ImagesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {
    @GET("rest/?method=flickr.photos.search")
    suspend fun getPhotos(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("api_key") api_key: String=Constants.FLickerKEY,
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perpage: Int=10
    ): ImagesResponseDTO
}