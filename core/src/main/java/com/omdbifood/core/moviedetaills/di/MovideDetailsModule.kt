package com.omdbifood.core.moviedetaills.di

import com.omdbifood.core.moviedetaills.data.local.MovieDetailsResponseToMovieDetailsMapper
import com.omdbifood.core.moviedetaills.data.local.MovieDetailsToMovieDetailsEntityMapper
import com.omdbifood.core.moviedetaills.data.local.datasource.MovieDetailsLocalDataSource
import com.omdbifood.core.moviedetaills.data.local.datasource.MovieDetailsLocalDataSourceImpl
import com.omdbifood.core.moviedetaills.data.remote.datasource.MovieDetailsRemoteDataSource
import com.omdbifood.core.moviedetaills.data.remote.datasource.MovieDetailsRemoteDataSourceImpl
import com.omdbifood.core.moviedetaills.data.remote.service.MovieDetailsService
import com.omdbifood.core.moviedetaills.data.repository.MovieDetailsRepository
import com.omdbifood.core.moviedetaills.data.repository.MovieDetailsRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailsModule = module {
    factory<MovieDetailsLocalDataSource> {
        MovieDetailsLocalDataSourceImpl(
            dao = get(),
            mapperToModel = MovieDetailsResponseToMovieDetailsMapper(),
            mapperToEntity = MovieDetailsToMovieDetailsEntityMapper()
        )
    }
    factory<MovieDetailsRemoteDataSource> {
        MovieDetailsRemoteDataSourceImpl(
            service = get<Retrofit>().create(MovieDetailsService::class.java),
            localDataSource = get()
        )
    }
    factory<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}
