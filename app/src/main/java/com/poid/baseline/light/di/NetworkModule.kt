package com.poid.baseline.light.di


import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.poid.baseline.light.BuildConfig
import com.poid.baseline.light.data.remote.ApiService
import com.poid.baseline.light.data.remote.NetworkConnectionInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideNetworkConnectionInterceptor(androidContext()) }
    single { provideRetrofit(get(), get()) }
    single { provideMoshi() }
    single { provideDefaultOkhttpClient(get(), get()) }
    single { provideApiService(get()) }
}

fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideNetworkConnectionInterceptor(
    context: Context,
) = NetworkConnectionInterceptor(context)

fun provideDefaultOkhttpClient(
    logging: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor,
) = OkHttpClient
    .Builder()
    .addInterceptor(logging)
    .addInterceptor(networkConnectionInterceptor)
    .build()

fun provideMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

fun provideRetrofit(
    client: OkHttpClient,
    moshi: Moshi,
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.SERVER_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

fun provideApiService(
    retrofit: Retrofit,
): ApiService = retrofit.create(ApiService::class.java)