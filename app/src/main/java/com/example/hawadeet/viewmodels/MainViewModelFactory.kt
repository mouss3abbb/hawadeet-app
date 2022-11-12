package com.example.hawadeet.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hawadeet.repositories.MainRepository

class MainViewModelFactory(val repository: MainRepository, val view: View): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository,view) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}