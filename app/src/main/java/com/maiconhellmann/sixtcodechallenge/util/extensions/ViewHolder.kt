package com.maiconhellmann.sixtcodechallenge.util.extensions

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

/*
 * This file is part of SixtCodeChallenge.
 *
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
fun RecyclerView.ViewHolder.getString(@StringRes resId: Int, vararg: Any): String {
    return this.itemView.context.getString(resId, vararg)
}