package com.maiconhellmann.sixtcodechallenge.local.mapper

import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.local.model.CarCache

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
object CarCacheMapper {
    fun map(car: Car): CarCache {
        return CarCache(
            id = car.id,
            carImageUrl = car.carImageUrl,
            color = car.color,
            name = car.name,
            fuelLevel = car.fuelLevel,
            fuelType = car.fuelType,
            group = car.group,
            innerCleanliness = car.innerCleanliness,
            latitude = car.latitude,
            longitude = car.longitude,
            licensePlate = car.licensePlate,
            make = car.make,
            modelIdentifier = car.modelIdentifier,
            modelName = car.modelName,
            series = car.series,
            transmission = car.transmission)
    }

    fun map(car: CarCache): Car {
        return Car(
            id = car.id,
            carImageUrl = car.carImageUrl,
            color = car.color,
            name = car.name,
            fuelLevel = car.fuelLevel,
            fuelType = car.fuelType,
            group = car.group,
            innerCleanliness = car.innerCleanliness,
            latitude = car.latitude,
            longitude = car.longitude,
            licensePlate = car.licensePlate,
            make = car.make,
            modelIdentifier = car.modelIdentifier,
            modelName = car.modelName,
            series = car.series,
            transmission = car.transmission)
    }

    fun mapCarCacheList(list: List<Car>): List<CarCache> {
        return list.map { map(it) }
    }

    fun mapCarList(list: List<CarCache>): List<Car> {
        return list.map { map(it) }
    }
}