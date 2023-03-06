package com.omdbifood.core.results.favorites.data.local

import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapperTestStubs.imdbIdStub
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapperTestStubs.posterUrlStub
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapperTestStubs.resultEntityStub
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapperTestStubs.titleStub
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapperTestStubs.typeStub
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapperTestStubs.yearStub
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ResultEntityToResultMapperTest {

    private val mapper = ResultEntityToResultMapper()

    @Test
    fun `should map MovieDetailsResponse to MovieDetails`() {
        // When
        val result = mapper.map(resultEntityStub)

        // Then
        with(result) {
            assertEquals(titleStub, title)
            assertEquals(yearStub, year)
            assertEquals(imdbIdStub, imdbId)
            assertEquals(typeStub.typeStr, type)
            assertEquals(posterUrlStub, posterUrl)
        }
    }
}
