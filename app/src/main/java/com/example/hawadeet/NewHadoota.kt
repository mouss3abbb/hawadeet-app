package com.example.hawadeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hawadeet.databinding.ActivityNewHadootaBinding
import com.example.hawadeet.viewmodels.NewHadootaFactory
import com.example.hawadeet.viewmodels.NewHadootaViewModel
import kotlinx.android.synthetic.main.activity_new_hadoota.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewHadoota : AppCompatActivity() {
    private lateinit var binding: ActivityNewHadootaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewHadootaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newHadootaViewModel: NewHadootaViewModel by viewModels {
            NewHadootaFactory(this,binding.root)
        }
        binding.newHadootaViewModel = newHadootaViewModel
        binding.editText = binding.newHadoota

    }


}