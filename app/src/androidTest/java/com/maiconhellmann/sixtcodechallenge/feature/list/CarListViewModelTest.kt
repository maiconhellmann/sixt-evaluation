package com.maiconhellmann.sixtcodechallenge.feature.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maiconhellmann.sixtcodechallenge.di.presentationModuleTest
import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.entity.FuelType
import com.maiconhellmann.sixtcodechallenge.entity.TransmissionType
import com.maiconhellmann.sixtcodechallenge.usecase.GetCarUseCase
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

class CarListViewModelTest : AutoCloseKoinTest() {

    val viewModel: CarListViewModel by inject()

    val car = Car(
        id = "WMWSW31030T222518",
        modelIdentifier = "mini",
        modelName = "MINI",
        name = "Vanessa",
        make = "BMW",
        group = "MINI",
        color = "midnight_black",
        series = "MINI",
        fuelType = FuelType.DIESEL,
        fuelLevel = 0.7,
        transmission = TransmissionType.MANUAL,
        licensePlate = "M-VO0259",
        latitude = 48.134557,
        longitude = 11.576921,
        innerCleanliness = "REGULAR",
        carImageUrl = "https://cdn.sixt.io/codingtask/images/mini.png")

    //A JUnit Test Rule that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
    //https://developer.android.com/reference/android/arch/core/executor/testing/InstantTaskExecutorRule
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        stopKoin()

        startKoin {
            modules(presentationModuleTest)
        }
        declareMock<GetCarUseCase> {
            given(this.getCarList(true)).willReturn(Single.just(listOf(car)))
        }
    }

    @Test
    fun viewModelTest() {

        assert(viewModel.state.value == ViewState.Loading)

        viewModel.getCarList((true))

        assert(viewModel.state.value is ViewState.Success)

        with(viewModel.state.value as ViewState.Success) {
            assert(data.isNotEmpty())
            assert(data.first().car.id == car.id)
            //Continue
        }
    }

    @After
    fun cleanup() {
        stopKoin()
    }
}