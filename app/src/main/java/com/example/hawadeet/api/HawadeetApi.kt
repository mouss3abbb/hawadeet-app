package com.example.hawadeet.api

import com.example.hawadeet.Hadoota
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface HawadeetApi {

    @GET("show-random")
    fun getHadoota(): Call<Hadoota>

    @POST("add-hadoota")
    fun addHadoota(
        @Body hadoota: Hadoota
    ): Call<Hadoota>

    @GET("show-all")
    fun getHawadeet(): Call<List<Hadoota>>

    @GET("show-all/{status}")
    fun getHawadeet(
        @Path("status") status: String
    ): Call<List<Hadoota>>
}