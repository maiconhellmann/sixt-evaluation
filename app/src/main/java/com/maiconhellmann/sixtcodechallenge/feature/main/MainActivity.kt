package com.maiconhellmann.sixtcodechallenge.feature.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.databinding.ActivityMainBinding
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListViewModel
import com.maiconhellmann.sixtcodechallenge.util.extensions.hasLocationPermission
import com.maiconhellmann.sixtcodechallenge.util.extensions.requestLocationPermission
import com.maiconhellmann.sixtcodechallenge.util.extensions.visible
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * This file is part of SixtCodeChallenge.
 *
 * Created by maiconhellmann on 02/08/2019
 *
 * (c) 2019
 */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CarListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        getNavController()?.let { nav->
            setupActionBar(nav)
            setupBottomNavMenu(nav)
        }

        setupViewModel()

        requestLocationPermission()

    }

    private fun setupViewModel() {
        viewModel.state.observe(this, Observer { state->
            when(state) {
                is ViewState.Success -> {
                    setVisibilities(showToolbar = true, showNavView = true)
                }
                is ViewState.Failed -> {
                    setVisibilities(showToolbar = true, showNavView = true)
                }
                is ViewState.Loading -> {
                    setVisibilities(showProgressBar = true)
                }
            }
        })
        viewModel.getCarList()
        viewModel.getLocation()
    }

    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showToolbar: Boolean = false,
        showNavView: Boolean = false
    ) {
        binding.progressBar.visible(showProgressBar)
        binding.toolbar.visible(showToolbar)
        binding.bottomNavView.visible(showNavView)
    }

    private fun getNavController() =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)?.navController

    private fun setupActionBar(navController: NavController) {
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = binding.bottomNavView
        bottomNav.setupWithNavController(navController)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (hasLocationPermission()) {
            viewModel.getLocation()
        }
    }
}
