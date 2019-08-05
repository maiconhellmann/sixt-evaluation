package com.maiconhellmann.sixtcodechallenge.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.maiconhellmann.sixtcodechallenge.entity.Location
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import com.google.android.gms.location.LocationResult



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

        val locationRequest = LocationRequest
            .create().apply {
                interval = 1000
            }

        val mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    Log.d("LocationService", "LocationService, location: $location")
                    updates.onNext(Location(location.latitude, location.longitude))
                }
            }
        }
        fusedLocationClient?.requestLocationUpdates(locationRequest, mLocationCallback, null)

        fusedLocationClient?.lastLocation?.addOnSuccessListener { location->
            location?.let {
                Log.d("LocationService", "LocationService, location: $it")
                updates.onNext(Location(it.latitude, it.longitude))
            }
        }
        return updates
    }
}
