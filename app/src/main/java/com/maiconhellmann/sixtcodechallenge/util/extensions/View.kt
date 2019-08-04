package com.maiconhellmann.sixtcodechallenge.util.extensions

import android.view.View
import android.view.ViewTreeObserver

fun View.visible(visible: Boolean = false) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.afterLayout(onLayoutMeasure: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(selfReference {
        ViewTreeObserver.OnGlobalLayoutListener {
            onLayoutMeasure.invoke()
            viewTreeObserver.removeOnGlobalLayoutListener(self)
        }
    })
}

class SelfReference<T>(initializer: SelfReference<T>.() -> T) {
    val self: T by lazy {
        inner ?: throw IllegalStateException()
    }

    private val inner = initializer()
}

fun <T> selfReference(initializer: SelfReference<T>.() -> T): T {
    return SelfReference(initializer).self
}