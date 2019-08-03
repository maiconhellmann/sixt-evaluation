package com.maiconhellmann.sixtcodechallenge.feature.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.databinding.CarListFragmentBinding
import com.maiconhellmann.sixtcodechallenge.util.extensions.toast
import com.maiconhellmann.sixtcodechallenge.util.extensions.visible
import com.maiconhellmann.sixtcodechallenge.util.viewmodel.ViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 03/08/2019
 * 
 * (c) 2019 
 */class CarListFragment : Fragment() {
    private lateinit var binding: CarListFragmentBinding
    private val carAdapter: CarListAdapter by inject()
    private val viewModel: CarListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.car_list_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setupRecyclerView()
        setupViewModel()
        setupSwipeRefresh()
    }

    /**
     * Setup the Swipe refresh mechanism triggering the viewModel.
     */
    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCarList(true)
        }
    }

    private fun setupViewModel() {
        viewModel.state.observe(this, Observer { state->
            when(state) {
                is ViewState.Success -> {
                    carAdapter.cars = state.data
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

    /**
     * Show a really basic error message to the user
     */
    private fun showError(throwable: Throwable) {
        view?.context?.toast(throwable.toString())
        Log.e(CarListFragment::class.java.simpleName, "Error", throwable)
    }

    /**
     * Setup the recycleView layout manager if needed and set the adapter.
     */
    private fun setupRecyclerView() = with(binding.recyclerView) {
        adapter = carAdapter
    }

    /**
     * Centralized method to set the component visibilities.
     */
    private fun setVisibilities(
        showProgressBar: Boolean = false,
        showList: Boolean = false,
        showError: Boolean = false,
        isRefreshing: Boolean = false
    ) {
        binding.progressBar.visible(showProgressBar)
        binding.recyclerView.visible(showList)
        binding.btnTryAgain.visible(showError)
        binding.swipeRefresh.isRefreshing = isRefreshing
    }
}