package com.maiconhellmann.sixtcodechallenge.remote.api

import com.maiconhellmann.sixtcodechallenge.remote.model.CarPayload
import io.reactivex.Single
import retrofit2.http.GET

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */interface CarServiceApi {

    @GET("/codingtask/cars")
    fun getCarList(): Single<List<CarPayload>>
}