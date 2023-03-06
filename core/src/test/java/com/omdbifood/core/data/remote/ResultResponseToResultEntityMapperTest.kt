package com.omdbifood.core.data.remote

import com.omdbifood.core.data.remote.ResultResponseToResultEntityMapperStubs.resultImdbIdStub
import com.omdbifood.core.data.remote.ResultResponseToResultEntityMapperStubs.resultPosterUrlStub
import com.omdbifood.core.data.remote.ResultResponseToResultEntityMapperStubs.resultTitleStub
import com.omdbifood.core.data.remote.ResultResponseToResultEntityMapperStubs.resultTypeStub
import com.omdbifood.core.data.remote.ResultResponseToResultEntityMapperStubs.resultYearStub
import com.omdbifood.core.data.remote.ResultResponseToResultEntityMapperStubs.resultsResponseStub
import com.omdbifood.core.results.domain.ResultTypeEntity
import com.omdbifood.core.results.data.remote.ResultResponseToResultEntityMapper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

internal class ResultResponseToResultEntityMapperTest {

    private val mapper = ResultResponseToResultEntityMapper()

    @Test
    fun `should map ResultsResponse to ResultEntity`() {
        // When
        val result = mapper.map(resultsResponseStub)

        // Then
        assertTrue(result.isNotEmpty())
        with(result.first()) {
            assertEquals(resultTitleStub, title)
            assertEquals(resultYearStub, year)
            assertEquals(resultImdbIdStub, imdbId)
            assertEquals(ResultTypeEntity.fromString(resultTypeStub), type)
            assertEquals(resultPosterUrlStub, posterUrl)
        }
    }
}
