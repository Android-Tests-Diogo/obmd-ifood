package com.omdbifood.core.results.domain

data class ResultEntity(
    val title: String,
    val year: String,
    val imdbId: String,
    val type: ResultTypeEntity,
    val posterUrl: String,
    val isFavorite: Boolean
)
