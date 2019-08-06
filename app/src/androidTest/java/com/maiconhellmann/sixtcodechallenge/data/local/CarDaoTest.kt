package com.maiconhellmann.sixtcodechallenge.data.local

import androidx.test.platform.app.InstrumentationRegistry
import com.maiconhellmann.sixtcodechallenge.data.di.cacheDataModuleTest
import com.maiconhellmann.sixtcodechallenge.local.database.CarDao
import com.maiconhellmann.sixtcodechallenge.local.model.CarCache
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get

/*
 * This file is part of hellmann-architeture.
 *
 * Created by maiconhellmann on 09/06/2019
 *
 * (c) 2019
 */
class CarDaoTest : AutoCloseKoinTest() {

    val carDao: CarDao = get()

    @Before
    fun before() {
        stopKoin()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
            modules(cacheDataModuleTest)
        }
    }

    @Test
    fun carDaoTesting() {
        carDao.apply {

            val carCache = CarCache(
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

            insertAll(listOf(carCache))

            with(getAll().test()) {
                assertValue {
                    it.isNotEmpty()
                }

                assertValue { it.first().id == carCache.id }
                assertValue { it.first().carImageUrl == carCache.carImageUrl }
                assertValue { it.first().fuelType == carCache.fuelType }
                assertValue { it.first().transmission == carCache.transmission }
                assertValue { it.first().uuid > 0L } //will be auto generated
                // ....
            }

        }
    }

    @After
    fun cleanup() {
        stopKoin()
    }
}