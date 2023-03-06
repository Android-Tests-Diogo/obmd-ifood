package com.omdbifood.core.moviedetaills.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omdbifood.core.moviedetaills.data.local.model.MovieDetails

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movieDetails: MovieDetails)

    @Query("DELETE FROM movies_details WHERE imdb_id=:movieId")
    fun delete(movieId: String)

    @Query("SELECT * FROM movies_details WHERE imdb_id=:movieId")
    fun getMovieDetails(movieId: String): MovieDetails
}
