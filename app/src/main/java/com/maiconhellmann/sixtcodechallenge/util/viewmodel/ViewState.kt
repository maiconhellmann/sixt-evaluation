package com.maiconhellmann.sixtcodechallenge.util.viewmodel

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 * Sealed class to handle ViewStates.
 *
 * When an error happens it will return a [ViewState.Failed] with a [ViewState.Failed.throwable] field
 *
 * When it is subscribed it will return a [ViewState.Loading]
 *
 * When the call was successfuly made it will return a [ViewState.Success] with a [ViewState.Success.data] field
 *
 * Usage:
    yourUseCase.fetchSomething()
    .compose(StateMachineSingle())
    .observeOn(uiScheduler)
    .subscribeBy(onSuccess = { state ->
        when (state) {
            is View.Success -> //set your data using state.data
            is View.Loading -> //set loading
            is View.Failed -> //set error using state.throwable
        }
    })
 *
 */
sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failed(val throwable: Throwable) : ViewState<Nothing>()
}

class StateMachineSingle<T>: SingleTransformer<T, ViewState<T>> {

    override fun apply(upstream: Single<T>): SingleSource<ViewState<T>> {
        return upstream
            .map {
                ViewState.Success(it) as ViewState<T>
            }
            .onErrorReturn {
                ViewState.Failed(it)
            }
            .doOnSubscribe {
                ViewState.Loading
            }
    }
}