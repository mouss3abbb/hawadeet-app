package com.example.hawadeet.viewmodels

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hawadeet.NewHadoota
import com.example.hawadeet.R
import com.example.hawadeet.adapters.HawadeetAdapter
import com.example.hawadeet.repositories.MainRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel(
    private val repository: MainRepository,
    @SuppressLint("StaticFieldLeak") private val view: View
    ): ViewModel() {
    private var selectedCategory = ""
    var hawadeetAdapterLiveData = MutableLiveData<HawadeetAdapter>().apply {
        value = HawadeetAdapter(repository.provideHawadeet(selectedCategory))
    }

    @SuppressLint("StaticFieldLeak")
    private var previousButton: Button = view.findViewById(R.id.all_category)
    fun buttonListener(view: View){
        val it = view as Button
        previousButton.background = getDrawable(repository.context,R.drawable.button_unchecked)
        it.background = getDrawable(repository.context,R.drawable.button_checked)
        previousButton = it
        selectedCategory = if(it.text.toString() == "All") "" else it.text.toString()
        hawadeetAdapterLiveData.value = HawadeetAdapter(repository.provideHawadeet(selectedCategory))
    }

    fun addNewHadoota(){
        repository.context.startActivity(
            Intent(
               repository.context,
               NewHadoota::class.java
            )
        )
    }


}