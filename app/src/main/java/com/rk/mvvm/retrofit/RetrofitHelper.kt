package com.rk.mvvm.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val headerInterceptor: Interceptor
    get() = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer j1O1luD59q3dmkJDkz1lcI-7v5hGOjiS7XiHelHgTFmGDyHXiw")
            .header("Content-Type", "application/json")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()


object RetrofitHelper {
    private const val BASE_URL = "https://crudapi.co.uk/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

