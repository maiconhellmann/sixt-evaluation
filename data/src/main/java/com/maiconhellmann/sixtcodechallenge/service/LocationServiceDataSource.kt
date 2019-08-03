package com.maiconhellmann.sixtcodechallenge.service

import com.maiconhellmann.sixtcodechallenge.entity.Location
import io.reactivex.Observable

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */
interface LocationServiceDataSource {
    fun getLocationUpdates(): Observable<Location>
}

class LocationServiceDataSourceImpl(private val locationListener: CurrentLocationListener): LocationServiceDataSource {
    override fun getLocationUpdates(): Observable<Location> {
        return locationListener.startListening()
    }
}