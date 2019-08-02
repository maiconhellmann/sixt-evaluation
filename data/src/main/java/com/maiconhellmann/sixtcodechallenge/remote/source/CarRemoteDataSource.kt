package com.maiconhellmann.sixtcodechallenge.remote.source

import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.remote.api.CarServiceApi
import com.maiconhellmann.sixtcodechallenge.remote.mapper.CarPayloadMapper
import io.reactivex.Single

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
interface CarRemoteDataSource {
    fun getCarList(): Single<List<Car>>
}

class CarRemoteDataSourceImpl(private val api: CarServiceApi) : CarRemoteDataSource {
    override fun getCarList(): Single<List<Car>> {
        return api.getCarList().map {
            CarPayloadMapper.mapPayloadList(it)
        }
    }
}