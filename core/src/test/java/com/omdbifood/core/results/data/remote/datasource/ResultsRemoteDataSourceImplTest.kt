package com.omdbifood.core.results.data.remote.datasource

import app.cash.turbine.test
import com.omdbifood.core.results.data.remote.ResultResponseToResultEntityMapper
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.imdbIdStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.invalidCharStubStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.movieNameStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.pageStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.posterUrlStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.resultResponseStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.titleStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.typeStub
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImplStubs.yearStub
import com.omdbifood.core.results.data.remote.exceptions.ResultsExceptions
import com.omdbifood.core.results.data.remote.model.ResultsErrorResponse
import com.omdbifood.core.results.data.remote.service.ResultsService
import com.omdbifood.core.results.domain.ResultTypeEntity
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ResultsRemoteDataSourceImplTest {

    private val serviceMock = mockk<ResultsService>()
    private val mapperMock = ResultResponseToResultEntityMapper()
    private val remoteDataSource = ResultsRemoteDataSourceImpl(
        service = serviceMock,
        mapper = mapperMock
    )

    @Test
    fun `getResults should emit list of ResultEntity when service response success`() = runTest {
        // Given
        coEvery { serviceMock.fetchMovies(movieNameStub, pageStub) } returns resultResponseStub

        // When
        remoteDataSource.getResults(movieNameStub, pageStub).test {
            // Then
            with(expectMostRecentItem()) {
                assertTrue(resultResponseStub.results.size == size)
                with(first()) {
                    assertEquals(titleStub, title)
                    assertEquals(yearStub, year)
                    assertEquals(imdbIdStub, imdbId)
                    assertEquals(ResultTypeEntity.fromString(typeStub), type)
                    assertEquals(posterUrlStub, posterUrl)
                }
            }
        }
    }

    @Test
    fun `getResults should throw NoResultsFound exception when deserlializer response error`() =
        runTest {
            // Given
            coEvery {
                serviceMock.fetchMovies(
                    movieNameStub,
                    pageStub
                )
            } returns mockk<ResultsErrorResponse>()

            // When
            remoteDataSource.getResults(movieNameStub, pageStub).test {
                // Then
                with(awaitError()) {
                    assertTrue(this is ResultsExceptions.NoResultsFound)
                }
            }
        }

    @Test
    fun `getResults should throw MinCharsAccepted exception input not has min chars count`() {
        runTest {
            // When
            remoteDataSource.getResults(
                invalidCharStubStub,
                pageStub
            ).test {
                // Then
                with(awaitError()) {
                    assertTrue(this is ResultsExceptions.MinCharsAccepted)
                }
            }
        }
    }
}
