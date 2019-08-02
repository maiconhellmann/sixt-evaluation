package com.maiconhellmann.sixtcodechallenge.util.extensions

import android.view.View

fun View.visible(visible: Boolean = false) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.afterLayout(onLayoutMeasure: ()-> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener {
        onLayoutMeasure.invoke()
        this.viewTreeObserver.removeOnGlobalLayoutListener(onLayoutMeasure)
    }
}
