package com.omdbifood.core.results.favorites.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omdbifood.core.results.favorites.data.local.model.Result

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movieDetails: Result)

    @Query("DELETE FROM favorites WHERE imdb_id=:movieId")
    fun delete(movieId: String)

    @Query("DELETE FROM favorites")
    fun deleteAll()

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): List<Result>
}
