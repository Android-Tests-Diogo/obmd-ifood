package com.omdbifood.core.deserializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * This class is to help KotlinSerialization
 * to decode a boolean with captalize string("True")
 */
object BooleanDeserializer : KSerializer<Boolean> {

    override fun deserialize(decoder: Decoder): Boolean {
        return decoder.decodeString().toBoolean()
    }

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.encodeBoolean(value)
    }

    override val descriptor: SerialDescriptor =
        kotlinx.serialization.descriptors.PrimitiveSerialDescriptor(
            "Response",
            PrimitiveKind.BOOLEAN
        )
}
