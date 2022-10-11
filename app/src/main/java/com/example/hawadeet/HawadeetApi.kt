package com.example.hawadeet

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface HawadeetApi {

    @GET("show-random")
    fun getHadoota(): Call<Hadoota>

    @POST("add-hadoota")
    fun addHadoota(
        @Body hadoota: Hadoota
    ): Call<Hadoota>
}