package com.omdbifood.core.moviedetaills.data.local

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.moviedetaills.data.local.model.MovieDetails
import com.omdbifood.core.moviedetaills.domain.MovieDetailsEntity

class MovieDetailsToMovieDetailsEntityMapper : Mapper<MovieDetails, MovieDetailsEntity> {

    override fun map(input: MovieDetails): MovieDetailsEntity =
        with(input) {
            MovieDetailsEntity(
                title = title,
                runtime = runtime,
                genre = genre,
                plot = plot,
                language = language,
                posterUrl = posterUrl,
                imdbRating = imdbRating,
                imdbID = imdbID,
                type = type,
                dvd = dvd
            )
        }
}
