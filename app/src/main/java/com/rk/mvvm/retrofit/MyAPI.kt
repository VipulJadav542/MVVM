package com.rk.mvvm.retrofit

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MyAPI {
    @GET("/api/v1/task")
    fun getTask(
//        @Header("Content-Type") contentType: String,
//        @Header("Authorization") authorization: String
    ): Call<myData>
//    base_url/quotes?page=1

    @POST("/api/v1/task")
    fun addTast(
//        @Header("Content-Type") contentType: String,
//        @Header("Authorization") authorization: String,
        @Body newDestination: List<insertItem>
    ): Call<insertItem>

    @DELETE("/api/v1/task/{uuid}")
    fun deleteTask(
//        @Header("Content-Type") contentType: String,
//        @Header("Authorization") authorization: String,
        @Path("uuid") taskId: String
    ): Call<Void> // Adjust the return type as needed

    @PUT("/api/v1/task/{uuid}")
    fun updateTask(
//        @Header("Content-Type") contentType: String,
//        @Header("Authorization") authorization: String,
        @Path("uuid") uuid: String,
        @Body requestBody: RequestBody
    ): Call<insertItem>
}