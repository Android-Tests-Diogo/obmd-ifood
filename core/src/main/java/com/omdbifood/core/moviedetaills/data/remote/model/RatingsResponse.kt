package com.omdbifood.core.moviedetaills.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingsResponse(
    @SerialName("Source")
    val source: String,
    @SerialName("Value")
    val value: String
)
