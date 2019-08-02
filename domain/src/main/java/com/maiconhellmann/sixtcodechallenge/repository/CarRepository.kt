package com.maiconhellmann.sixtcodechallenge.repository

import com.maiconhellmann.sixtcodechallenge.entity.Car
import io.reactivex.Single

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */interface CarRepository {
    fun getCarList(forceUpdate: Boolean): Single<Car>
}