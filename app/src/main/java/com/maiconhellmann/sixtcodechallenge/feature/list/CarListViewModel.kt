package com.maiconhellmann.sixtcodechallenge.feature.list

import androidx.lifecycle.MutableLiveData
import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.entity.Location
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

    var currentPosition = MutableLiveData<Location>()

    fun getCarList(forceUpdate: Boolean = false) {
        disposables += useCase.getCarList(forceUpdate = forceUpdate)
            .doOnSubscribe {
                if(!forceUpdate) {
                    state.postValue(ViewState.Loading)
                }
            }
            .compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(onSuccess = {
                state.postValue(it)
            })
    }

    fun getLocation() {
        disposables += useCase.getLocationUpdates()
            .observeOn(uiScheduler)
            .subscribeBy(
                onNext = {
                    it?.let { location->
                        currentPosition.postValue(location)
                    }
                }
            )
    }
}