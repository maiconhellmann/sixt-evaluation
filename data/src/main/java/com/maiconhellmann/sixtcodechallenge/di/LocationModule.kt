package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.service.CurrentLocationListener
import com.maiconhellmann.sixtcodechallenge.service.LocationServiceDataSource
import com.maiconhellmann.sixtcodechallenge.service.LocationServiceDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */
val locationModule = module {
    single {
        CurrentLocationListener(androidContext())
    }
    factory<LocationServiceDataSource> { LocationServiceDataSourceImpl(locationListener = get()) }
}