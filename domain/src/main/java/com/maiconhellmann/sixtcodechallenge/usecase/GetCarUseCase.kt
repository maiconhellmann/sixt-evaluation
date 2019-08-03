package com.maiconhellmann.sixtcodechallenge.usecase

import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.repository.CarRepository
import io.reactivex.Scheduler
import io.reactivex.Single

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
class GetCarUseCase(private val repository: CarRepository, private val scheduler: Scheduler) {
    fun getCarList(forceUpdate: Boolean): Single<List<Car>> {
        return repository.getCarList(forceUpdate).subscribeOn(scheduler)
    }
    fun getLocationUpdates() = repository.getLocationUpdates()
}