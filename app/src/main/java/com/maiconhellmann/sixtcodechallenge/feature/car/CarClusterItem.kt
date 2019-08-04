package com.maiconhellmann.sixtcodechallenge.feature.car

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 04/08/2019
 * 
 * (c) 2019 
 */data class CarClusterItem(
    private val latitude: Double, private val longitude: Double, private val title: String, private val snippet: String
) : ClusterItem {
    override fun getSnippet() = snippet

    override fun getTitle() = title

    override fun getPosition() = LatLng(latitude, longitude)
}