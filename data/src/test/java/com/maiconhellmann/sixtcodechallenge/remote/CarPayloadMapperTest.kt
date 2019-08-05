package com.maiconhellmann.sixtcodechallenge.remote

import com.maiconhellmann.sixtcodechallenge.remote.mapper.CarPayloadMapper
import com.maiconhellmann.sixtcodechallenge.remote.model.CarPayload
import org.junit.Test

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 05/06/2019
 * 
 * (c) 2019 
 */class CarPayloadMapperTest {
    @Test
    fun `ArticlePayload to Article`() {
        val payload = CarPayload(
            id = "WMWSW31030T222518",
            modelIdentifier = "mini",
            modelName = "MINI",
            name = "Vanessa",
            make = "BMW",
            group = "MINI",
            color = "midnight_black",
            series = "MINI",
            fuelType = "D",
            fuelLevel = 0.7,
            transmission = "M",
            licensePlate = "M-VO0259",
            latitude = 48.134557,
            longitude = 11.576921,
            innerCleanliness = "REGULAR",
            carImageUrl = "https://cdn.sixt.io/codingtask/images/mini.png")

        val mapped = CarPayloadMapper.map(payload)

        assert(mapped.id == payload.id)
        assert(mapped.carImageUrl == payload.carImageUrl)
        assert(mapped.color == payload.color)
        assert(mapped.fuelLevel == payload.fuelLevel)
        assert(mapped.fuelType.value == payload.fuelType)
        assert(mapped.transmission.value == payload.transmission)
        assert(mapped.latitude == payload.latitude)
        assert(mapped.longitude == payload.longitude)
        // ....
    }
}