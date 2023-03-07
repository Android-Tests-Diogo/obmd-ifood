package com.omdbifood.core.results.data.remote.model

import com.omdbifood.core.deserializer.BooleanDeserializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsErrorResponse(
    @SerialName("Error")
    val error: String,
    @SerialName("Response")
    @Serializable(with = BooleanDeserializer::class)
    val response: Boolean
) : ResultsResponse()
