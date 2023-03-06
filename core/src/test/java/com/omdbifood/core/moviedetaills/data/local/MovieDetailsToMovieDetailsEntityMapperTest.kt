package com.omdbifood.core.moviedetaills.data.local

import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.dvdStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.genreStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.imdbIDStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.imdbRatingStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.languageStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.movieDetailsStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.plotStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.posterUrlStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.runtimeStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.titleStub
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapperTestStubs.typeStub
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieDetailsToMovieDetailsEntityMapperTest {

    private val mapper = MovieDetailsToMovieDetailsEntityMapper()

    @Test
    fun `should map MovieDetails to MovieDetailsEntity`() {
        // When
        val result = mapper.map(movieDetailsStub)

        // Then
        with(result) {
            assertEquals(titleStub, title)
            assertEquals(runtimeStub, runtime)
            assertEquals(genreStub, genre)
            assertEquals(plotStub, plot)
            assertEquals(languageStub, language)
            assertEquals(posterUrlStub, posterUrl)
            assertEquals(imdbRatingStub, imdbRating)
            assertEquals(imdbIDStub, imdbID)
            assertEquals(typeStub, type)
            assertEquals(dvdStub, dvd)
        }
    }
}
