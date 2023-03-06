package com.omdbifood.home.presentation.viewmodel

import androidx.lifecycle.Observer
import com.omdbifood.android.CoroutineTestRule
import com.omdbifood.android.LocalTestRule
import com.omdbifood.android.ViewModelTestRule
import com.omdbifood.home.presentation.viewmodel.HomeViewModelStubs.isFetchingResultsStub
import io.mockk.every
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val localTestRule = LocalTestRule()

    @get:Rule
    val viewModelRule = ViewModelTestRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val stateObserver: Observer<HomeUIState> by lazy { viewModelRule.getStateObserver() }
    private val actionObserver: Observer<HomeUIAction> by lazy { viewModelRule.getActionObserver() }
    private val stateObserverSlot = slot<HomeUIState>()
    private var statesCaptured: MutableList<HomeUIState> = mutableListOf()
    private val actionObserverSlot = slot<HomeUIAction>()
    private val actionsCaptured: MutableList<HomeUIAction> = mutableListOf()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel()

        every { stateObserver.onChanged(capture(stateObserverSlot)) } answers {
            statesCaptured.add(stateObserverSlot.captured)
        }

        every { actionObserver.onChanged(capture(actionObserverSlot)) } answers {
            actionsCaptured.add(actionObserverSlot.captured)
        }
    }

    @Test
    fun `onFetchResultsChanged should update State`() {
        // When
        homeViewModel.onFetchResultsChanged(isFetchingResultsStub)

        // Then
        assertEquals(statesCaptured.first().bottomLoadingVisibility, isFetchingResultsStub)
    }

    @Test
    fun `onFavoritesChanged should send Action UpdateFavoritesOnResultsPage`() {
        // When
        homeViewModel.onFavoritesChanged()

        // Then
        assertTrue(statesCaptured.isEmpty())
        assertTrue(actionsCaptured.first() is HomeUIAction.UpdateFavoritesOnResultsPage)
    }

    @Test
    fun `onFavoritesInResultsChanged should send Action UpdateFavoritesPage when FavoritesFragment ready`() {
        // Given
        homeViewModel.onFavoritesFragmentReady()

        // When
        homeViewModel.onFavoritesInResultsChanged()

        // Then
        assertTrue(statesCaptured.isEmpty())
        assertTrue(actionsCaptured.first() is HomeUIAction.UpdateFavoritesPage)
    }

    @Test
    fun `onFavoritesInResultsChanged should do nothing when FavoritesFragment is not ready `() {
        // When
        homeViewModel.onFavoritesInResultsChanged()

        // Then
        assertTrue(statesCaptured.isEmpty())
        assertTrue(actionsCaptured.isEmpty())
    }

    @After
    fun after() {
        statesCaptured.clear()
        actionsCaptured.clear()
    }
}
