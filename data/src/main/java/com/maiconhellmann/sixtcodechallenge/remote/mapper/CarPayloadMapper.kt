package com.maiconhellmann.sixtcodechallenge.remote.mapper

import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.entity.FuelType
import com.maiconhellmann.sixtcodechallenge.entity.TransmissionType
import com.maiconhellmann.sixtcodechallenge.remote.model.CarPayload

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */object CarPayloadMapper {
    fun map(payload: CarPayload): Car {
        return Car(
            id = payload.id,
            carImageUrl = payload.carImageUrl,
            color = payload.color,
            name = payload.name,
            fuelLevel = payload.fuelLevel,
            fuelType = FuelType.fromValue(payload.fuelType),
            group = payload.group,
            innerCleanliness = payload.innerCleanliness,
            latitude = payload.latitude,
            longitude = payload.longitude,
            licensePlate = payload.licensePlate,
            make = payload.make,
            modelIdentifier = payload.modelIdentifier,
            modelName = payload.modelName,
            series = payload.series,
            transmission = TransmissionType.fromValue(payload.transmission))
    }

    fun map(car: Car): CarPayload {
        return CarPayload(
            id = car.id,
            carImageUrl = car.carImageUrl,
            color = car.color,
            name = car.name,
            fuelLevel = car.fuelLevel,
            fuelType = car.fuelType.value,
            group = car.group,
            innerCleanliness = car.innerCleanliness,
            latitude = car.latitude,
            longitude = car.longitude,
            licensePlate = car.licensePlate,
            make = car.make,
            modelIdentifier = car.modelIdentifier,
            modelName = car.modelName,
            series = car.series,
            transmission = car.transmission.value
        )
    }

    fun mapPayloadList(list: List<CarPayload>): List<Car> {
        return list.map { map(it) }
    }

    fun mapCarList(list: List<Car>): List<CarPayload> {
        return list.map { map(it) }
    }
}