package com.omdbifood.core.results.data.remote.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ResultsResponseDeserializer : KSerializer<ResultsResponse> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        "ResultsResponse") {}

    override fun deserialize(decoder: Decoder): ResultsResponse {
        return kotlin.runCatching {
            decoder.decodeSerializableValue(ResultsSuccessResponse.serializer())
        }.getOrElse {
            ResultsErrorResponse(
                error = "Movie not found!",
                response = false
            )
        }
    }

    override fun serialize(encoder: Encoder, value: ResultsResponse) {
        encoder.encodeSerializableValue(ResultsResponse.serializer(), value)
    }
}
