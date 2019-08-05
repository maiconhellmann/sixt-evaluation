package com.maiconhellmann.sixtcodechallenge.data.di

import com.maiconhellmann.sixtcodechallenge.local.database.CarDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModuleTest = module {
    single { CarDatabase.createDatabaseInMemory(androidContext()) }
}