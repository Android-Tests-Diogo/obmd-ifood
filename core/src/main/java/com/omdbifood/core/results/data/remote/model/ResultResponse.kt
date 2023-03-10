package com.omdbifood.core.results.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("Title")
    val title: String,
    @SerialName("Year")
    val year: String,
    @SerialName("imdbID")
    val imdbId: String,
    @SerialName("Type")
    val type: String,
    @SerialName("Poster")
    val posterUrl: String,
)
