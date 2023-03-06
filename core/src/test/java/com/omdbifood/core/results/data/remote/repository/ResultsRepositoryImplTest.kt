package com.omdbifood.core.results.data.remote.repository

import app.cash.turbine.test
import com.omdbifood.core.results.data.local.ResultsTemporaryDataSource
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSource
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.imdbIdStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.movieNameStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.nextPageStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.pageStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.posterUrlStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.resultResultEntityStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.titleStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.typeStub
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImplTestStubs.yearStub
import com.omdbifood.core.results.domain.ResultEntity
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ResultsRepositoryImplTest {

    private val remoteDataSourceMock = mockk<ResultsRemoteDataSource>()
    private val temporaryDataSourceMock = mockk<ResultsTemporaryDataSource>(relaxed = true)
    private val repository = ResultsRepositoryImpl(
        remoteDataSource = remoteDataSourceMock,
        temporaryDataSource = temporaryDataSourceMock
    )

    @Test
    fun `getResults should emit list ResultEntity when call first time`() = runTest {
        // Given
        every { remoteDataSourceMock.getResults(movieNameStub, pageStub) } returns flowOf(mockk())
        every { temporaryDataSourceMock.getTemporaryResults() } returns resultResultEntityStub
        every { temporaryDataSourceMock.renewTemporaryResults(mockk()) } just runs

        // When
        repository.getResults(movieNameStub, pageStub).test {
            // Then
            with(expectMostRecentItem()) {
                TestCase.assertTrue(resultResultEntityStub.size == size)
                with(first()) {
                    assertEquals(titleStub, title)
                    assertEquals(yearStub, year)
                    assertEquals(imdbIdStub, imdbId)
                    assertEquals(typeStub, type)
                    assertEquals(posterUrlStub, posterUrl)
                }
            }
        }
    }

    @Test
    fun `getResults should emit list ResultEntity when is search more results`() = runTest {
        // Given
        val expectedListStub = mockk<List<ResultEntity>>()
        every { remoteDataSourceMock.getResults(movieNameStub, pageStub) } returns flowOf(
            expectedListStub
        )
        every { remoteDataSourceMock.getResults(movieNameStub, nextPageStub) } returns flowOf(
            expectedListStub
        )
        every { temporaryDataSourceMock.getTemporaryResults() } returns resultResultEntityStub
        every { temporaryDataSourceMock.renewTemporaryResults(mockk()) } just runs

        // When
        repository.getResults(movieNameStub, pageStub).test {
            // Then
            repository.getResults(movieNameStub, nextPageStub).test {
                expectMostRecentItem()
                verify { temporaryDataSourceMock.addMoreTemporaryResults(expectedListStub) }
            }

            expectMostRecentItem()
        }
    }

    @Test
    fun `getResults should emit list ResultEntity when page and input is same after first time`() =
        runTest {
            // Given
            every { remoteDataSourceMock.getResults(movieNameStub, pageStub) } returns flowOf(
                mockk()
            )
            every { temporaryDataSourceMock.getTemporaryResults() } returns resultResultEntityStub

            // When
            repository.getResults(movieNameStub, pageStub).test {
                // Then
                repository.getResults(movieNameStub, pageStub).test {
                    assertEquals(expectMostRecentItem(), resultResultEntityStub)
                    verify(exactly = 1) { remoteDataSourceMock.getResults(movieNameStub, pageStub) }
                }

                expectMostRecentItem()
            }
        }
}
