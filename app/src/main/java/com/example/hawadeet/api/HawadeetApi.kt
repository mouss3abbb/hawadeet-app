package com.example.hawadeet.api

import com.example.hawadeet.Hadoota
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface HawadeetApi {

    @POST("add-hadoota")
    suspend fun addHadoota(
        @Body hadoota: Hadoota
    ): Response<Hadoota>

    @GET("show-all")
    suspend fun getHawadeet(): Response<List<Hadoota>>
}