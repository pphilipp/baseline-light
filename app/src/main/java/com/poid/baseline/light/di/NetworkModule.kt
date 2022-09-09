package com.poid.baseline.light.di


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.poid.baseline.light.BuildConfig
import com.poid.baseline.light.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideRetrofit(get(), get()) }
    single { provideMoshi() }
    single { provideDefaultOkhttpClient(get()) }
    single { provideApiService(get()) }
}

fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideDefaultOkhttpClient(
    logging: HttpLoggingInterceptor
) = OkHttpClient
    .Builder()
    .addInterceptor(logging)
    .build()

fun provideMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

fun provideRetrofit(
    client: OkHttpClient,
    moshi: Moshi
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.SERVER_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

fun provideApiService(
    retrofit: Retrofit
): ApiService = retrofit.create(ApiService::class.java)