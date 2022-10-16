package com.example.hawadeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hadoota_item.*
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

        add_new_hadoota.setOnClickListener {
            startActivity(Intent(this,NewHadoota::class.java))
        }

        api = retrofit.create(HawadeetApi::class.java)
        val getHawadeetCall = api.getHawadeet()
        Log.d("TAG", "onCreate")
        getHawadeetCall.enqueue(object : Callback<List<Hadoota>>{
                override fun onResponse(call: Call<List<Hadoota>>, response: Response<List<Hadoota>>) {
                    val adapter = response.body()?.let { HawadeetAdapter(it) }
                    recycler_view.adapter = adapter
                    recycler_view.layoutManager = LinearLayoutManager(applicationContext)
                }

                override fun onFailure(call: Call<List<Hadoota>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

}
