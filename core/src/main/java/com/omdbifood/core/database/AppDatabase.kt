package com.omdbifood.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omdbifood.core.moviedetaills.data.local.daos.MovieDetailsDao
import com.omdbifood.core.moviedetaills.data.local.model.MovieDetails
import com.omdbifood.core.results.favorites.data.local.dao.FavoritesDao
import com.omdbifood.core.results.favorites.data.local.model.Result

@Database(
    entities = [
        Result::class,
        MovieDetails::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao

    abstract fun favoritesDao(): FavoritesDao
}
