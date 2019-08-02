package com.maiconhellmann.sixtcodechallenge

import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.local.source.CarCacheDataSource
import com.maiconhellmann.sixtcodechallenge.remote.source.CarRemoteDataSource
import com.maiconhellmann.sixtcodechallenge.repository.CarRepository
import io.reactivex.Single

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */class CarRepositoryImpl(
    private val cacheDataSource: CarCacheDataSource,
    private val remoteDataSource: CarRemoteDataSource
) : CarRepository {
    override fun getCarList(forceUpdate: Boolean): Single<List<Car>> {
        return if (forceUpdate) {
            getCarListRemote(forceUpdate)
        } else {
            cacheDataSource.getCarCacheList().flatMap { list ->
                when {
                    list.isEmpty() -> getCarListRemote(false)
                    else -> Single.just(list)
                }
            }
        }
    }

    private fun getCarListRemote(forceUpdate: Boolean): Single<List<Car>> {
        return remoteDataSource.getCarList().flatMap { list ->
            if (forceUpdate) {
                cacheDataSource.updateData(list)
            } else {
                cacheDataSource.insertData(list)
            }
            Single.just(list)
        }
    }
}