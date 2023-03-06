package com.omdbifood.core.results.domain

enum class ResultTypeEntity(val typeStr: String) {
    MOVIE("movie"),
    SERIES("series"),
    GAME("game"),
    EPISODE("episode");

    companion object {
        fun fromString(type: String): ResultTypeEntity = ResultTypeEntity.valueOf(type.uppercase())
    }
}
