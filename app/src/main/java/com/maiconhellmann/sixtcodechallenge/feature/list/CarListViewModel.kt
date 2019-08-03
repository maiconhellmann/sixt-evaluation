package com.maiconhellmann.sixtcodechallenge.feature.list

import androidx.lifecycle.MutableLiveData
import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.usecase.GetCarUseCase
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.BaseViewModel
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.StateMachineSingle
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */class CarListViewModel(
    private val useCase: GetCarUseCase, private val uiScheduler: Scheduler
) : BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Car>>>().apply {
        value = ViewState.Loading
    }

    fun getCarList(forceUpdate: Boolean = false) {
        disposables += useCase.getCarList(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(onSuccess = {
                state.postValue(it)
            })
    }
}