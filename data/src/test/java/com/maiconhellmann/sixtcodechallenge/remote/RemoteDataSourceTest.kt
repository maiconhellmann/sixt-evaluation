package com.maiconhellmann.sixtcodechallenge.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.remote.api.CarServiceApi
import com.maiconhellmann.sixtcodechallenge.remote.mock.CarMock
import com.maiconhellmann.sixtcodechallenge.remote.model.CarPayload
import com.maiconhellmann.sixtcodechallenge.remote.source.CarRemoteDataSource
import com.maiconhellmann.sixtcodechallenge.remote.source.CarRemoteDataSourceImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: CarRemoteDataSource
    private lateinit var serverApi: CarServiceApi
    private lateinit var carList: List<Car>

    @Before
    fun prepare() {
        serverApi = mock(CarServiceApi::class.java)
        remoteDataSource = CarRemoteDataSourceImpl(serverApi)

        val listType = object : TypeToken<List<CarPayload>>() { }.type
        carList = Gson().fromJson(CarMock.json, listType)
    }

    @Test
    fun `article list is empty`() {
        `when`(serverApi.getCarList()).then {
            Single.just(emptyList<CarPayload>())
        }
        with(remoteDataSource.getCarList().test()) {
            assertValue {
                it.isEmpty()
            }
            assertValueCount(1)
        }
    }

    @Test
    fun `article list NOT empty`() {
        `when`(serverApi.getCarList()).then {
            Single.just(carList)
        }

        with(remoteDataSource.getCarList().test()) {
            assertValue { it.isNotEmpty() }
            assertValueCount(1)
        }
    }
}