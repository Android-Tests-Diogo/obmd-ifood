package com.omdbifood.core.moviedetaills.data.local

import com.omdbifood.core.moviedetaills.data.remote.model.MovieDetailsResponse
import com.omdbifood.core.moviedetaills.data.remote.model.RatingsResponse

internal object MovieDetailsResponseToMovieDetailsMapperTestStubs {

    const val titleStub = "titleStub"
    const val yearStub = "yearStub"
    const val ratedStub = "ratedStub"
    const val releasedStub = "releasedStub"
    const val runtimeStub = "runtimeStub"
    const val genreStub = "genreStub"
    const val directorStub = "directorStub"
    const val writerStub = "writerStub"
    const val actorsStub = "actorsStub"
    const val plotStub = "plotStub"
    const val languageStub = "languageStub"
    const val countryStub = "countryStub"
    const val awardsStub = "awardsStub"
    const val posterUrlStub = "posterUrlStub"
    const val ratingsResponseSourceStub = "ratingsResponseSourceStub"
    const val ratingsResponseValueStub = "ratingsResponseValueStub"
    const val metaScoreStub = "metaScoreStub"
    const val imdbRatingStub = "imdbRatingStub"
    const val imdbVotesStub = "imdbVotesStub"
    const val imdbIDStub = "imdbIDStub"
    const val typeStub = "typeStub"
    const val dvdStub = "dvdStub"
    const val boxOfficeStub = "boxOfficeStub"
    const val productionStub = "productionStub"
    const val websiteStub = "websiteStub"

    val movieDetailsResponseStub = MovieDetailsResponse(
        title = titleStub,
        year = yearStub,
        rated = ratedStub,
        released = releasedStub,
        runtime = runtimeStub,
        genre = genreStub,
        director = directorStub,
        writer = writerStub,
        actors = actorsStub,
        plot = plotStub,
        language = languageStub,
        country = countryStub,
        awards = awardsStub,
        posterUrl = posterUrlStub,
        ratings = listOf(
            RatingsResponse(
                source = ratingsResponseSourceStub,
                value = ratingsResponseValueStub
            )
        ),
        metaScore = metaScoreStub,
        imdbRating = imdbRatingStub,
        imdbVotes = imdbVotesStub,
        imdbID = imdbIDStub,
        type = typeStub,
        dvd = dvdStub,
        boxOffice = boxOfficeStub,
        production = productionStub,
        website = websiteStub,
        response = true
    )
}
