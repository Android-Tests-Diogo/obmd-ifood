package com.omdbifood.sample.di

import android.content.Context
import androidx.room.Room
import com.omdbifood.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { getAppDatabase(androidContext()) }
}

fun getAppDatabase(appContext: Context) =
    Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        name = "omdb_ifood_database"
    ).build()
