package com.maiconhellmann.sixtcodechallenge.local.source

import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.local.database.CarDao
import com.maiconhellmann.sixtcodechallenge.local.mapper.CarCacheMapper
import io.reactivex.Single

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
interface CarCacheDataSource {
    fun getCarCacheList(): Single<List<Car>>
    fun updateData(carList: List<Car>)
    fun insertData(carList: List<Car>)
}

class CarCacheDataSourceImpl(private val carDao: CarDao) : CarCacheDataSource {
    override fun updateData(carList: List<Car>) {
        carDao.updateDate(CarCacheMapper.mapCarCacheList(carList))
    }

    override fun insertData(carList: List<Car>) {
        carDao.insertAll(CarCacheMapper.mapCarCacheList(carList))
    }

    override fun getCarCacheList(): Single<List<Car>> {
        return carDao.getAll().map { CarCacheMapper.mapCarList(it) }
    }
}