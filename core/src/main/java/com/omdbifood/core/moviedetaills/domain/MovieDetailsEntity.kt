package com.omdbifood.core.moviedetaills.domain

data class MovieDetailsEntity(
    val title: String,
    val runtime: String,
    val genre: String,
    val plot: String,
    val language: String,
    val posterUrl: String,
    val imdbRating: String,
    val imdbID: String,
    val type: String,
    val dvd: String
)
