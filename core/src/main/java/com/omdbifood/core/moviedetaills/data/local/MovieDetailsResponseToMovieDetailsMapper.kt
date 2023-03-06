package com.omdbifood.core.moviedetaills.data.local

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.moviedetaills.data.local.model.MovieDetails
import com.omdbifood.core.moviedetaills.data.local.model.Ratings
import com.omdbifood.core.moviedetaills.data.remote.model.MovieDetailsResponse

class MovieDetailsResponseToMovieDetailsMapper : Mapper<MovieDetailsResponse, MovieDetails> {

    override fun map(input: MovieDetailsResponse): MovieDetails =
        with(input) {
            MovieDetails(
                title = title,
                year = year,
                rated = rated,
                released = released,
                runtime = runtime,
                genre = genre,
                director = director,
                writer = writer,
                actors = actors,
                plot = plot,
                language = language,
                country = country,
                awards = awards,
                posterUrl = posterUrl,
                /*ratings = ratings.map {
                    Ratings(
                        source = it.source,
                        value = it.value
                    )
                },*/
                metaScore = metaScore,
                imdbRating = imdbRating,
                imdbVotes = imdbVotes,
                imdbID = imdbID,
                type = type,
                dvd = dvd,
                boxOffice = boxOffice,
                production = production,
                website = website
            )
        }
}
