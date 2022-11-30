package com.example.hawadeet

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hawadeet.adapters.HawadeetAdapter
import com.example.hawadeet.api.HawadeetApi
import com.example.hawadeet.databinding.ActivityMainBinding
import com.example.hawadeet.db.HawadeetDatabase
import com.example.hawadeet.repositories.MainRepository
import com.example.hawadeet.viewmodels.MainViewModel
import com.example.hawadeet.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val NO_HAWADEET = Hadoota("No hawadeet found", "")
private val URL = "https://hawadeet-api.onrender.com/"
private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
var api = retrofit.create(HawadeetApi::class.java)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainViewModel: MainViewModel by viewModels {
            MainViewModelFactory(MainRepository.getInstance(this), binding.root)
        }

        binding.mainViewModel = mainViewModel
        mainViewModel.hawadeetAdapterLiveData.observe(this, {
            binding.recyclerView.adapter = mainViewModel.hawadeetAdapterLiveData.value
        })

    }


}
