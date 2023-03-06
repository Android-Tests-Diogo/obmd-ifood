package com.omdbifood.home.di

import com.omdbifood.android.koin.FeatureModule
import com.omdbifood.android.resources.ResourceProvider
import com.omdbifood.core.results.di.resultsDataModule
import com.omdbifood.core.results.favorites.di.favoritesDataModule
import com.omdbifood.home.favorites.domain.FavoritesUseCase
import com.omdbifood.home.favorites.presentation.viewmodel.FavoritesViewModel
import com.omdbifood.home.presentation.viewmodel.HomeViewModel
import com.omdbifood.home.results.domain.ResultsInteractor
import com.omdbifood.home.results.domain.ResultsUseCase
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModule : FeatureModule {

    override val modules: List<Module> = listOf(
        resultsDataModule,
        favoritesDataModule,
        module {
            viewModel {
                HomeViewModel()
            }
            viewModel {
                FavoritesViewModel(
                    favoritesUseCase = get()
                )
            }
            factory {
                FavoritesUseCase(
                    favoritesRepository = get()
                )
            }
            viewModel {
                ResultsViewModel(
                    resultsInteractor = get(),
                    resourceProvider = get()
                )
            }
            factory {
                ResultsInteractor(
                    favoritesUseCase = get(),
                    resultsUseCase = get()
                )
            }
            factory {
                ResultsUseCase(
                    resultsRepository = get()
                )
            }
            factory {
                FavoritesUseCase(
                    favoritesRepository = get()
                )
            }
        }
    )
}
