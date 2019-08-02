package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.usecase.GetCarUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/*
 * This file is part of SixtCodeChallenge.
 *
 * Created by maiconhellmann on 02/08/2019
 *
 * (c) 2019
 */
val useCaseModule = module {

    factory {
        GetCarUseCase(
            repository = get(), scheduler = Schedulers.io())
    }
}

val domainModule = listOf(useCaseModule)