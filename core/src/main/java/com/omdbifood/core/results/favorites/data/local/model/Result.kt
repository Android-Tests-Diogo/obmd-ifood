package com.omdbifood.core.results.favorites.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favorites", indices = [Index(value = ["imdb_id"], unique = true)])
data class Result(
    val title: String,
    val year: String,
    @PrimaryKey
    @ColumnInfo("imdb_id")
    val imdbId: String,
    val type: String,
    @ColumnInfo("poster_url")
    val posterUrl: String
)
