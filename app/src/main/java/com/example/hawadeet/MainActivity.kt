package com.example.hawadeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


lateinit var retrofit: Retrofit
lateinit var api: HawadeetApi

class MainActivity : AppCompatActivity() {
    private val URL = "https://hawadeet-api.herokuapp.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(HawadeetApi::class.java)
        val getHadootaCall = api.getHadoota()
        Log.d("TAG", "onCreate")
        getHadootaCall.enqueue(object : Callback<Hadoota>{
            override fun onResponse(call: Call<Hadoota>, response: Response<Hadoota>) {
                Log.d("TAG", "onResponse")
                hadoota_status.text = response.body()?.status
                hadoota_body.text = response.body()?.body
            }

            override fun onFailure(call: Call<Hadoota>, t: Throwable) {
                Log.d("TAG", "onFailure")
            }
        })

        add_new_hadoota.setOnClickListener {
            startActivity(Intent(this,NewHadoota::class.java))
        }

    }
}