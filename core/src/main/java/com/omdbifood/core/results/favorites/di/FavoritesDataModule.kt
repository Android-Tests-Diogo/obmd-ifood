package com.omdbifood.core.results.favorites.di

import com.omdbifood.core.database.AppDatabase
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapper
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapper
import com.omdbifood.core.results.favorites.data.local.datasource.FavoritesLocalDataSource
import com.omdbifood.core.results.favorites.data.local.datasource.FavoritesLocalDataSourceImpl
import com.omdbifood.core.results.favorites.data.local.repository.FavoritesRepository
import com.omdbifood.core.results.favorites.data.local.repository.FavoritesRepositoryImpl
import org.koin.dsl.module

val favoritesDataModule = module {
    factory<FavoritesLocalDataSource> {
        FavoritesLocalDataSourceImpl(
            dao = get<AppDatabase>().favoritesDao(),
            mapperToResult = ResultEntityToResultMapper(),
            mapperToEntityList = ResultListToResultEntityListMapper()
        )
    }
    factory<FavoritesRepository> {
        FavoritesRepositoryImpl(
            favoritesLocalDataSourceMock = get(),
            temporaryResultsDataSourceMock = get()
        )
    }
}
