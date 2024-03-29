package com.maiconhellmann.sixtcodechallenge.feature.car

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.maiconhellmann.sixtcodechallenge.databinding.CarMapFragmentBinding
import com.maiconhellmann.sixtcodechallenge.feature.list.CarItemModel
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListFragment
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListViewModel
import com.maiconhellmann.sixtcodechallenge.util.extensions.afterLayout
import com.maiconhellmann.sixtcodechallenge.util.extensions.toast
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.google.maps.android.clustering.ClusterManager
import com.maiconhellmann.sixtcodechallenge.R



/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */class CarMapFragment : Fragment(), OnMapReadyCallback {

    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    private var map: GoogleMap?= null
    private lateinit var binding: CarMapFragmentBinding
    private val viewModel: CarListViewModel by sharedViewModel()
    private var clusterManager: ClusterManager<CarClusterItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.car_map_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        with(binding.mapView) {
            onCreate(mapViewBundle)
            getMapAsync(this@CarMapFragment)
        }
    }

    @SuppressLint("MissingPermission")
    private fun setupViewModel() {
        viewModel.state.observe(this, Observer { state->
            when(state) {
                is ViewState.Success -> {
                    createMarkers(state.data)
                }
                is ViewState.Failed -> {
                    showError(state.throwable)
                }
            }
        })

        viewModel.currentPosition.observe(this, Observer {
            if (it != null) {
                map?.isMyLocationEnabled = true
            }
        })
    }

    private fun createMarkers(list: List<CarItemModel>) {
        Log.d(CarListFragment::class.java.simpleName, "createMarkers")

        setupClusterManager()
        map?.clear()

        val builder = LatLngBounds.Builder()

        list.map {
            clusterManager?.addItem(CarClusterItem(it.car.latitude, it.car.longitude, getString(R.string.car_name, it.car.make, it.car.modelName), ""))
            MarkerOptions()
                .position(LatLng(it.car.latitude, it.car.longitude))
                .title(getString(R.string.car_name, it.car.make, it.car.modelName))
        }.forEach {
            builder.include(it.position)
            //map?.addMarker(it)
        }

        val bounds = builder.build()
        val padding = context?.resources?.getDimensionPixelSize(R.dimen.map_padding) ?: 100
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        map?.moveCamera(cu)

        clusterManager?.cluster()
    }

    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e(CarListFragment::class.java.simpleName, "Error", throwable)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        Log.d(CarListFragment::class.java.simpleName, "onMapReady")

        map = googleMap
        map?.also {
            with(it.uiSettings) {
                isIndoorLevelPickerEnabled = false
                isMyLocationButtonEnabled = true
                isMapToolbarEnabled = true
                isCompassEnabled = true
                isZoomControlsEnabled = false
                isRotateGesturesEnabled = true
                isScrollGesturesEnabled = true
                isTiltGesturesEnabled = false
                isZoomGesturesEnabled = true
                isMyLocationButtonEnabled = true
            }
        }

        //Make sure the view is ready
        binding.mapView.afterLayout {
            setupViewModel()
        }
    }

    private fun setupClusterManager() {
        clusterManager = ClusterManager(context, map)
        clusterManager?.clearItems()
        map?.setOnCameraIdleListener(clusterManager)
        map?.setOnMarkerClickListener(clusterManager)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }

        binding.mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}