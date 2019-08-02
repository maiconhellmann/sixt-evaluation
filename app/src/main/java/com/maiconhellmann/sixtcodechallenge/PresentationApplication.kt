package com.maiconhellmann.sixtcodechallenge

import android.app.Application
import com.maiconhellmann.sixtcodechallenge.di.dataModules
import com.maiconhellmann.sixtcodechallenge.di.domainModule
import com.maiconhellmann.sixtcodechallenge.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*
 * This file is part of SixtCodeChallenge.
 *
 * Created by maiconhellmann on 02/08/2019
 *
 * (c) 2019
 */
class PresentationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Starts Koin dependency injection
        startKoin {
            // declare used Android context
            androidContext(this@PresentationApplication)
            // declare modules
            modules(dataModules + domainModule + presentationModule)
        }
    }
}