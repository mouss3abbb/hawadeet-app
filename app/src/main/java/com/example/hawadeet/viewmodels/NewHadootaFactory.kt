package com.example.hawadeet.viewmodels

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewHadootaFactory(val context: Context, val view: View): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NewHadootaViewModel::class.java)) {
            return NewHadootaViewModel(context,view) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}