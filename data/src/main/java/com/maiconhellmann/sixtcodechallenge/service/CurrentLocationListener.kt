package com.maiconhellmann.sixtcodechallenge.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.maiconhellmann.sixtcodechallenge.entity.Location
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */
class CurrentLocationListener(context: Context) {

    private val updates = BehaviorSubject.create<Location>()

    private val fusedLocationClient: FusedLocationProviderClient? = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun startListening(): Observable<Location> {
        Log.d("LocationService", "startListening")
        fusedLocationClient?.lastLocation?.addOnSuccessListener { location->
            location?.let {
                Log.d("LocationService", "LocationService, location: $it")
                updates.onNext(Location(it.latitude, it.longitude))
            }
        }
        return updates
    }
}
