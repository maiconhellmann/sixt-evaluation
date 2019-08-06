package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.feature.list.CarListAdapter
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListViewModel
import com.maiconhellmann.sixtcodechallenge.usecase.GetCarUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module
import org.mockito.Mockito

val presentationModuleTest = module {
    factory { CarListAdapter() }

    factory {
        CarListViewModel(useCase = get(), uiScheduler = Schedulers.trampoline())
    }

    factory { Mockito.mock(GetCarUseCase::class.java) }
}