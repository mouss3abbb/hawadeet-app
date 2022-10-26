package com.example.hawadeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_hadoota.*
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
    private var checkedButton = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHawadeet()
        all_category.setOnClickListener {
            buttonListener(it,"")
        }
        happy_category.setOnClickListener {
            buttonListener(it,happy_category.text.toString())
        }
        sad_category.setOnClickListener {
            resetButtons()
            buttonListener(it,sad_category.text.toString())
        }
        motivational_category.setOnClickListener {
            buttonListener(it,motivational_category.text.toString())
        }
        other_category.setOnClickListener {
            buttonListener(it,other_category.text.toString())
        }
        bored_category.setOnClickListener {
            buttonListener(it,bored_category.text.toString())
        }
        educational_category.setOnClickListener {
            buttonListener(it,educational_category.text.toString())
        }
        achievement_category.setOnClickListener {
            buttonListener(it,achievement_category.text.toString())
        }
        fun_category.setOnClickListener {
            buttonListener(it,fun_category.text.toString())
        }
        add_new_hadoota.setOnClickListener {
            startActivity(Intent(this, NewHadoota::class.java))
        }

    }

    private fun buttonListener(it: View, text: String){
        resetButtons()
        it.background = getDrawable(R.drawable.button_checked)
        checkedButton = text
        getHawadeet()
    }

    private fun getHawadeet(){
        Log.d("TAG", "getHawadeet ")
        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(HawadeetApi::class.java)
        var getHawadeetCall = if(checkedButton != ""){
            api.getHawadeet(checkedButton)
        }else{
            api.getHawadeet()
        }
        Log.d("TAG", "onCreate")
        getHawadeetCall.enqueue(object : Callback<List<Hadoota>>{
            override fun onResponse(call: Call<List<Hadoota>>, response: Response<List<Hadoota>>) {
                if (response.body().isNullOrEmpty()){
                    recycler_view.adapter = HawadeetAdapter(listOf(Hadoota(body = "No hawadeet found", status = "")))
                    recycler_view.layoutManager = LinearLayoutManager(applicationContext)
                    return
                }
                val adapter = response.body()?.let { HawadeetAdapter(it) }
                recycler_view.adapter = adapter
                recycler_view.layoutManager = LinearLayoutManager(applicationContext)
            }

            override fun onFailure(call: Call<List<Hadoota>>, t: Throwable) {
                Toast.makeText(applicationContext,"Network error",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun resetButtons() {
        all_category.background = getDrawable(R.drawable.button_unchecked)
        happy_category.background = getDrawable(R.drawable.button_unchecked)
        sad_category.background = getDrawable(R.drawable.button_unchecked)
        motivational_category.background = getDrawable(R.drawable.button_unchecked)
        bored_category.background = getDrawable(R.drawable.button_unchecked)
        educational_category.background = getDrawable(R.drawable.button_unchecked)
        fun_category.background = getDrawable(R.drawable.button_unchecked)
        achievement_category.background = getDrawable(R.drawable.button_unchecked)
        other_category.background = getDrawable(R.drawable.button_unchecked)
    }

}
