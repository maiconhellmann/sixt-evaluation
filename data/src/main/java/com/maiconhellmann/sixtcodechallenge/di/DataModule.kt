package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.CarRepositoryImpl
import com.maiconhellmann.sixtcodechallenge.repository.CarRepository
import org.koin.dsl.module

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */

val repositoryModule = module {
    //Car
    factory<CarRepository> {
        CarRepositoryImpl(cacheDataSource = get(), remoteDataSource = get())
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)
