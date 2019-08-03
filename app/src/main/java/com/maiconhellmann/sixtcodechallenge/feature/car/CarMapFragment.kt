package com.maiconhellmann.sixtcodechallenge.feature.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.databinding.CarMapFragmentBinding

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */class CarMapFragment : Fragment() {
    private lateinit var binding: CarMapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.car_map_fragment, container, false)

        return binding.root
    }
}