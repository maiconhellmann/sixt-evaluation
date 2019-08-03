package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.feature.list.CarListAdapter
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
val presentationModule = module {
    factory { CarListAdapter() }

    viewModel {
        CarListViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread())
    }
}