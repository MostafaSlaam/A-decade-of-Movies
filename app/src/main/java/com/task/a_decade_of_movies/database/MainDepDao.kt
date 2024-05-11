package com.task.a_decade_of_movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel

@Dao
interface MainDepDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeps(vararg movies: MovieModel)

    @Query("DELETE FROM movies")
    fun nukeMoviesTable()
}