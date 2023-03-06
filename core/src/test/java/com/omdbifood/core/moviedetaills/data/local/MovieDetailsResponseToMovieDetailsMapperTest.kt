package com.omdbifood.core.moviedetaills.data.local

import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.actorsStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.awardsStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.boxOfficeStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.countryStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.directorStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.dvdStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.genreStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.imdbIDStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.imdbRatingStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.imdbVotesStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.languageStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.metaScoreStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.movieDetailsResponseStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.plotStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.posterUrlStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.productionStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.ratedStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.ratingsResponseSourceStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.ratingsResponseValueStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.releasedStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.runtimeStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.titleStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.typeStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.websiteStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.writerStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapperTestStubs.yearStub
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal class MovieDetailsResponseToMovieDetailsMapperTest {

    private val mapper = MovieDetailsResponseToMovieDetailsMapper()

    @Test
    fun `should map MovieDetailsResponse to MovieDetails`() {
        // When
        val result = mapper.map(movieDetailsResponseStub)

        // Then
        with(result) {
            assertEquals(titleStub, title)
            assertEquals(yearStub, year)
            assertEquals(ratedStub, rated)
            assertEquals(releasedStub, released)
            assertEquals(runtimeStub, runtime)
            assertEquals(genreStub, genre)
            assertEquals(directorStub, director)
            assertEquals(writerStub, writer)
            assertEquals(actorsStub, actors)
            assertEquals(plotStub, plot)
            assertEquals(languageStub, language)
            assertEquals(countryStub, country)
            assertEquals(awardsStub, awards)
            assertEquals(posterUrlStub, posterUrl)
            assertEquals(metaScoreStub, metaScore)
            assertEquals(imdbRatingStub, imdbRating)
            assertEquals(imdbVotesStub, imdbVotes)
            assertEquals(imdbIDStub, imdbID)
            assertEquals(typeStub, type)
            assertEquals(dvdStub, dvd)
            assertEquals(boxOfficeStub, boxOffice)
            assertEquals(productionStub, production)
            assertEquals(websiteStub, website)
        }
    }
}
