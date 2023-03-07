package com.omdbifood.core.moviedetaills.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "movies_details", indices = [Index(value = ["imdb_id"], unique = true)])
@Serializable
data class MovieDetails(
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    @ColumnInfo("poster_url")
    val posterUrl: String,
    @ColumnInfo("meta_score")
    val metaScore: String,
    @ColumnInfo("imdb_rating")
    val imdbRating: String,
    @ColumnInfo("imdb_votes")
    val imdbVotes: String,
    @PrimaryKey
    @ColumnInfo("imdb_id")
    val imdbID: String,
    val type: String,
    val dvd: String,
    @ColumnInfo("box_office")
    val boxOffice: String,
    val production: String,
    val website: String
)
