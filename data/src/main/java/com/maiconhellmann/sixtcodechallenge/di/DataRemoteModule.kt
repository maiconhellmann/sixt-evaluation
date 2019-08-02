package com.maiconhellmann.sixtcodechallenge.di

import com.maiconhellmann.sixtcodechallenge.BuildConfig
import com.maiconhellmann.sixtcodechallenge.remote.api.CarServiceApi
import com.maiconhellmann.sixtcodechallenge.remote.source.CarRemoteDataSource
import com.maiconhellmann.sixtcodechallenge.remote.source.CarRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 * This file is part of SixtCodeChallenge.
 *
 * Created by maiconhellmann on 02/08/2019
 *
 * (c) 2019
 */

val remoteDataSourceModule = module {
    //OkHttp
    factory { providesOkHttpClient() }

    single {
        createWebService<CarServiceApi>(
            okHttpClient = get(), url = BuildConfig.BASE_URL)
    }
    factory<CarRemoteDataSource> {
        CarRemoteDataSourceImpl(
            api = get())
    }
}

fun providesOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor)
        .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient, url: String
): T {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(url).client(okHttpClient)
        .build().create(T::class.java)
}