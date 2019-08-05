package com.maiconhellmann.sixtcodechallenge.feature.list

import com.maiconhellmann.sixtcodechallenge.entity.Car

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 04/08/2019
 * 
 * (c) 2019 
 */class CarItemModel(val car: Car, val distance: Float? = null) {
    fun distanceInKilometers() = distance?.div(1000)
    fun isDistanceInMeters() = distance != null && distance < 1000
}