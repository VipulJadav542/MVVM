package com.rk.mvvm.RoomRetrofit.api

import com.rk.mvvm.RoomRetrofit.model.QuoteList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteList>
    //base_url/quotes?page=1


}