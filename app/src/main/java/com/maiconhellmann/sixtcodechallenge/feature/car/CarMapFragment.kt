package com.maiconhellmann.sixtcodechallenge.feature.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.databinding.CarMapFragmentBinding
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListFragment
import com.maiconhellmann.sixtcodechallenge.feature.list.CarListViewModel
import com.maiconhellmann.sixtcodechallenge.util.extensions.toast
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */class CarMapFragment : Fragment() {
    private lateinit var binding: CarMapFragmentBinding
    private val viewModel: CarListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.car_map_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, Observer { state->
            when(state) {
                is ViewState.Success -> {
                    //TODO update data
                    setVisibilities(showList = true)
                }
                is ViewState.Failed -> {
                    showError(state.throwable)
                    setVisibilities(showError = true)
                }
                is ViewState.Loading -> {
                    setVisibilities(showProgressBar = true)
                }
            }
        })
    }

    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showList: Boolean = false,
        showError: Boolean = false,
        isRefreshing: Boolean = false
    ) {
        //TODO setupt view visibilities
    }

    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e(CarListFragment::class.java.simpleName, "Error", throwable)
    }
}