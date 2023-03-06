package com.omdbifood.core.results.di

import com.omdbifood.core.results.data.local.ResultsTemporaryDataSource
import com.omdbifood.core.results.data.local.ResultsTemporaryDataSourceImpl
import com.omdbifood.core.results.data.remote.ResultResponseToResultEntityMapper
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSource
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSourceImpl
import com.omdbifood.core.results.data.remote.repository.ResultsRepository
import com.omdbifood.core.results.data.remote.repository.ResultsRepositoryImpl
import com.omdbifood.core.results.data.remote.service.ResultsService
import org.koin.dsl.module
import retrofit2.Retrofit

val resultsDataModule = module {
    single<ResultsTemporaryDataSource> {
        ResultsTemporaryDataSourceImpl()
    }
    factory<ResultsRemoteDataSource> {
        ResultsRemoteDataSourceImpl(
            service = get<Retrofit>().create(ResultsService::class.java),
            mapper = ResultResponseToResultEntityMapper()
        )
    }
    factory<ResultsRepository> {
        ResultsRepositoryImpl(
            remoteDataSource = get(),
            temporaryDataSource = get()
        )
    }
}
