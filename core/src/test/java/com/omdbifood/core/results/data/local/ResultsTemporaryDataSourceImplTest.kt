package com.omdbifood.core.results.data.local

import com.omdbifood.core.results.data.local.ResultsTemporaryDataSourceImplStubs.imdbIdStub
import com.omdbifood.core.results.data.local.ResultsTemporaryDataSourceImplStubs.temporaryResultsEntity1Stub
import com.omdbifood.core.results.data.local.ResultsTemporaryDataSourceImplStubs.temporaryResultsEntity2Stub
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class ResultsTemporaryDataSourceImplTest {

    private val dataSource = ResultsTemporaryDataSourceImpl()

    @Test(expected = Exception::class)
    fun `getResultToAddFavorites should trigger exception when not found item in list`() {
        // Given
        val movieIdStub = "movieIdStub"

        // When
        dataSource.getResultToAddFavorites(movieIdStub)
    }

    @Test
    fun `getResultToAddFavorites should emit ResultEntity when found item in list`() {
        // Given
        dataSource.addMoreTemporaryResults(listOf(temporaryResultsEntity1Stub))

        // When
        val result = dataSource.getResultToAddFavorites(imdbIdStub)

        // Then
        assertEquals(temporaryResultsEntity1Stub, result)
    }

    @Test
    fun `renewTemporaryResults should clear actual list and return new list`() {
        // Given
        dataSource.addMoreTemporaryResults(
            listOf(
                temporaryResultsEntity1Stub,
                temporaryResultsEntity2Stub
            )
        )

        // When
        dataSource.renewTemporaryResults(listOf(temporaryResultsEntity2Stub))

        // Then
        assertEquals(temporaryResultsEntity2Stub, dataSource.getTemporaryResults().first())
    }

    @Test
    fun `addMoreTemporaryResults should concat lists`() {
        // Given
        dataSource.addMoreTemporaryResults(listOf(temporaryResultsEntity1Stub))

        // When
        dataSource.addMoreTemporaryResults(listOf(temporaryResultsEntity2Stub))

        // Then
        assertEquals(temporaryResultsEntity1Stub, dataSource.getTemporaryResults().first())
        assertEquals(temporaryResultsEntity2Stub, dataSource.getTemporaryResults()[1])
    }
}
