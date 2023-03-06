package com.omdbifood.core.moviedetaills.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Ratings(
    val source: String,
    val value: String
)
