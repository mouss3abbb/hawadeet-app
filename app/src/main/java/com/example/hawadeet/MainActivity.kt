package com.example.hawadeet

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
import com.example.hawadeet.generated.callback.OnClickListener
import com.example.hawadeet.repositories.MainRepository
import com.example.hawadeet.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var previousButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        previousButton = binding.allCategory
        binding.mainViewModel = mainViewModel
        mainViewModel.hawadeetAdapterLiveData.observe(this) {
            binding.recyclerView.adapter = mainViewModel.hawadeetAdapterLiveData.value
        }
        binding.allCategory.setOnClickListener { buttonListener(it as Button) }
        binding.happyCategory.setOnClickListener { buttonListener(it as Button) }
        binding.sadCategory.setOnClickListener { buttonListener(it as Button) }
        binding.achievementCategory.setOnClickListener { buttonListener(it as Button) }
        binding.motivationalCategory.setOnClickListener { buttonListener(it as Button) }
        binding.otherCategory.setOnClickListener { buttonListener(it as Button) }
        binding.funCategory.setOnClickListener { buttonListener(it as Button) }
        binding.boredCategory.setOnClickListener { buttonListener(it as Button) }
        binding.educationalCategory.setOnClickListener { buttonListener(it as Button) }


    }

    fun buttonListener(it: Button) {
        mainViewModel.buttonListener(previousButton, it)
        previousButton = it
    }


}
