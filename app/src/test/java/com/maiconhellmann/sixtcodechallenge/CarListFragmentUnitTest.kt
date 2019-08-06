package com.maiconhellmann.sixtcodechallenge

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.entity.FuelType
import com.maiconhellmann.sixtcodechallenge.entity.TransmissionType
import com.maiconhellmann.sixtcodechallenge.feature.list.CarItemModel
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListAdapter
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListFragment
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@SmallTest
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class CarListFragmentUnitTest {

    private lateinit var mockNavController: NavController
    private lateinit var carList: FragmentScenario<CarListFragment>

    val carItemModel = CarItemModel(
        Car(
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
            carImageUrl = "https://cdn.sixt.io/codingtask/images/mini.png"), 1000F)

    @Before
    fun setUp() {
        mockNavController = Mockito.mock(NavController::class.java)
    }

    @Test
    fun `carListFragment test`() {
        carList = launchFragmentInContainer<CarListFragment>().onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)

            //Loading
            it.viewModel.state.postValue(ViewState.Loading)

            assert(it.view?.findViewById<View>(R.id.progressBar)?.isVisible == true)

            //Post a list with one item
            it.viewModel.state.postValue(
                ViewState.Success(
                    listOf(carItemModel)))

            //Not loading anymore
            assert(it.view?.findViewById<View>(R.id.progressBar)?.isVisible == false)

            it.view?.findViewById<RecyclerView>(R.id.recyclerView)?.apply {
                val index = 0

                //Url of the current item
                val url = (adapter as CarListAdapter).cars[index].car.carImageUrl ?: ""

                //Click current item
                onView(withId(R.id.recyclerView)).perform(
                    actionOnItemAtPosition<CarListAdapter.ViewHolder>(
                        index, click()))

                //navigation of the url of the item
                //TODO there is no directions to test here
                //val directions = CarListFragmentDirections.actionOpenWebview(url)

                //verify destination
                //Mockito.verify(mockNavController).navigate(directions)
            }
        }
    }
}
