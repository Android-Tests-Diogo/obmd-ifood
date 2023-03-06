package com.omdbifood.core.results.favorites.data.local

import com.omdbifood.core.results.domain.ResultTypeEntity
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapperStubs.favoritesResultStubs
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapperStubs.imdbIdStub
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapperStubs.posterUrlStub
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapperStubs.titleStub
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapperStubs.typeStub
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapperStubs.yearStub
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ResultListToResultEntityListMapperTest {

    private val mapper = ResultListToResultEntityListMapper()

    @Test
    fun `should`() {
        // When
        val result = mapper.map(favoritesResultStubs)

        // Then
        assertTrue(result.isNotEmpty())
        with(result.first()) {
            TestCase.assertEquals(titleStub, title)
            TestCase.assertEquals(yearStub, year)
            TestCase.assertEquals(imdbIdStub, imdbId)
            TestCase.assertEquals(ResultTypeEntity.MOVIE, type)
            TestCase.assertEquals(posterUrlStub, posterUrl)
        }
    }
}
