package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.local.database.CarDatabase
import com.maiconhellmann.sixtcodechallenge.local.source.CarCacheDataSource
import com.maiconhellmann.sixtcodechallenge.local.source.CarCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
val cacheDataModule = module {
    //Car cache database
    single { CarDatabase.createDatabase(androidContext()) }
    //Car cache data source
    factory<CarCacheDataSource> {
        CarCacheDataSourceImpl(carDao = get())
    }
}