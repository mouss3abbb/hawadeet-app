package com.example.hawadeet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.hawadeet.databinding.ActivityNewHadootaBinding
import com.example.hawadeet.viewmodels.NewHadootaViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewHadoota : AppCompatActivity() {
    private lateinit var binding: ActivityNewHadootaBinding
    private val newHadootaViewModel: NewHadootaViewModel by viewModels()
    private lateinit var previousButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("NewHadoota")
        binding = ActivityNewHadootaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        previousButton = binding.other
        binding.newHadootaViewModel = newHadootaViewModel
        binding.editText = binding.newHadoota

        binding.achievement.setOnClickListener { buttonListener(it as Button) }
        binding.other.setOnClickListener { buttonListener(it as Button) }
        binding.sad.setOnClickListener { buttonListener(it as Button) }
        binding.happy.setOnClickListener { buttonListener(it as Button) }
        binding.funButton.setOnClickListener { buttonListener(it as Button) }
        binding.educational.setOnClickListener { buttonListener(it as Button) }
        binding.motivational.setOnClickListener { buttonListener(it as Button) }
        binding.bored.setOnClickListener { buttonListener(it as Button) }

    }
    fun buttonListener(it: Button) {
        newHadootaViewModel.buttonListener(previousButton, it)
        previousButton = it
    }

}