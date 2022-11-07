package com.poid.baseline.light.di

import android.content.Context
import android.net.ConnectivityManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val systemServicesModule = module {
    single { provideConnectivityManager(androidContext()) }
}

fun provideConnectivityManager(context: Context): ConnectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
