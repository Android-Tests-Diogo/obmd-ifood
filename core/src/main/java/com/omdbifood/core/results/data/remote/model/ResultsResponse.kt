package com.omdbifood.core.results.data.remote.model

import com.omdbifood.core.deserializer.BooleanDeserializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class ResultsResponse(
    @SerialName("Search")
    val results: List<ResultResponse>,
    @SerialName("Response")
    @Serializable(with = BooleanDeserializer::class)
    val response: Boolean
)
