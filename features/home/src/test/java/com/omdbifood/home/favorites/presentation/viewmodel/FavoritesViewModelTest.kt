package com.omdbifood.home.favorites.presentation.viewmodel

import androidx.lifecycle.Observer
import com.omdbifood.android.CoroutineTestRule
import com.omdbifood.android.LocalTestRule
import com.omdbifood.android.ViewModelTestRule
import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.home.favorites.domain.FavoritesUseCase
import com.omdbifood.home.favorites.presentation.viewmodel.FavoritesViewModelStubs.resultEntityStub
import com.omdbifood.home.favorites.presentation.viewmodel.FavoritesViewModelStubs.titleStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavoritesViewModelTest {

    @get:Rule
    val localTestRule = LocalTestRule()

    @get:Rule
    val viewModelRule = ViewModelTestRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val favoritesUseCaseMock = mockk<FavoritesUseCase>()
    private lateinit var favoritesViewModel: FavoritesViewModel

    private val stateObserver: Observer<FavoriteUIState> by lazy { viewModelRule.getStateObserver() }
    private val actionObserver: Observer<FavoritesUIAction> by lazy { viewModelRule.getActionObserver() }
    private val stateObserverSlot = slot<FavoriteUIState>()
    private var stateCaptured = FavoriteUIState()
    private val actionObserverSlot = slot<FavoritesUIAction>()
    private lateinit var actionCaptured: FavoritesUIAction

    @Before
    fun setUp() {
        favoritesViewModel = FavoritesViewModel(
            favoritesUseCase = favoritesUseCaseMock
        )

        every { stateObserver.onChanged(capture(stateObserverSlot)) } answers {
            stateCaptured = stateObserverSlot.captured
        }

        every { actionObserver.onChanged(capture(actionObserverSlot)) } answers {
            actionCaptured = actionObserverSlot.captured
        }
    }

    @Test
    fun `getFavorites should emit State results from UseCase`() = runTest {
        // Given
        every { favoritesUseCaseMock.getAllFavorites() } returns flowOf(listOf(resultEntityStub))

        // When
        favoritesViewModel.getFavorites()

        // Then
        with(stateCaptured.results.first()) {
            assertEquals(FavoritesViewModelStubs.imdbIdStub, movieId)
            assertEquals(titleStub, title)
            assertTrue(favoriteVisibility)
        }
    }

    @Test
    fun `onFavoritesManagerClicked should emit State results from UseCase`() = runTest {
        // Given
        every { favoritesUseCaseMock.getAllFavorites() } returns flowOf(listOf(resultEntityStub))
        every {
            favoritesUseCaseMock.manageFavorite(titleStub)
        } returns flowOf(FlowGenericResult.Successful)

        // When
        favoritesViewModel.onFavoritesManagerClicked(titleStub)

        // Then
        with(stateCaptured.results.first()) {
            assertEquals(FavoritesViewModelStubs.imdbIdStub, movieId)
            assertEquals(titleStub, title)
            assertTrue(favoriteVisibility)
        }
        assertEquals(FavoritesUIAction.FavoritesChanged, actionCaptured)
    }
}
