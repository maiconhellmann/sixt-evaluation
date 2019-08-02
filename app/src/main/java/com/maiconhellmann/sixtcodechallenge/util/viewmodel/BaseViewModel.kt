package com.maiconhellmann.sixtcodechallenge.util.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/*
 * This file is part of SixtCodeChallenge.
 *
 * Created by maiconhellmann on 02/08/2019
 *
 * (c) 2019
 */
open class BaseViewModel: ViewModel() {
    /**
     * Reference to disposables used in the viewModels. When the ViewModel is cleared, it disposes everything.
     */
    val disposables = CompositeDisposable()

    override fun onCleared() {
        //dispose everything
        disposables.clear()

        super.onCleared()
    }
}