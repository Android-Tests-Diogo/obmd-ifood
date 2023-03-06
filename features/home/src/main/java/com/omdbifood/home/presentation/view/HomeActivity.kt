package com.omdbifood.home.presentation.view

import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayoutMediator
import com.omdbifood.android.viewbinding.viewBinding
import com.omdbifood.android.koin.KoinActivity
import com.omdbifood.android.viewmodel.extensions.onSendAction
import com.omdbifood.android.viewmodel.extensions.onSendState
import com.omdbifood.home.R
import com.omdbifood.home.databinding.ActivityHomeBinding
import com.omdbifood.home.di.HomeModule
import com.omdbifood.home.favorites.presentation.view.FavoritesFragment
import com.omdbifood.home.presentation.viewmodel.HomeUIAction
import com.omdbifood.home.presentation.viewmodel.HomeUIState
import com.omdbifood.home.presentation.viewmodel.HomeViewModel
import com.omdbifood.home.results.presentation.view.ResultsFragment
import com.omdbifood.home.results.presentation.view.ResultsFragmentPageStateAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

const val RESULTS_FRAGMENT = 0
const val MAX_PAGES = 2

class HomeActivity : KoinActivity(R.layout.activity_home, module = HomeModule) {

    private val viewModel by viewModel<HomeViewModel>()
    private val binding by viewBinding<ActivityHomeBinding>(R.id.root)
    private val resultsFragment = ResultsFragment(
        { isFetchingData ->
            viewModel.onFetchResultsChanged(isFetchingData)
        }
    ) {
        viewModel.onFavoritesInResultsChanged()
    }
    private val favoritesFragment = FavoritesFragment {
        viewModel.onFavoritesChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObservers()
        initializePageFragment()
    }

    private fun setupObservers() {
        onSendState(viewModel) {
            onStateUpdate(it)
        }
        onSendAction(viewModel) {
            onActionTrigger(it)
        }
    }

    private fun initializePageFragment() {
        if (binding.vpContent.adapter == null) {
            binding.vpContent.adapter = ResultsFragmentPageStateAdapter(
                view = this,
                createPage = { position ->
                    when (position) {
                        RESULTS_FRAGMENT -> resultsFragment
                        else -> favoritesFragment
                    }
                },
                pages = MAX_PAGES
            )
            TabLayoutMediator(binding.tlContent, binding.vpContent) { tab, position ->
                tab.text = when (position) {
                    RESULTS_FRAGMENT -> resources.getString(R.string.home_search)
                    else -> resources.getString(R.string.home_favorites)
                }
            }.attach()
        }

        favoritesFragment.isFragmentReady = {
            viewModel.onFavoritesFragmentReady()
        }
    }

    private fun onStateUpdate(state: HomeUIState) {
        binding.pbInfinityList.isVisible = state.bottomLoadingVisibility
    }

    private fun onActionTrigger(action: HomeUIAction) {
        when (action) {
            HomeUIAction.UpdateFavoritesOnResultsPage -> resultsFragment.syncData()
            HomeUIAction.UpdateFavoritesPage -> favoritesFragment.syncData()
        }
    }
}
