package com.maiconhellmann.sixtcodechallenge.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.databinding.ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        getNavController()?.let { nav->
            setupActionBar(nav)
            setupBottomNavMenu(nav)
        }
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
}
