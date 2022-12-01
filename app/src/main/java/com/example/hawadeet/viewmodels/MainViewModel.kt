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
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.internal.InjectedFieldSignature
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor (val repository: MainRepository ) : ViewModel() {
    private var selectedCategory = ""
    var hawadeetAdapterLiveData = MutableLiveData<HawadeetAdapter>().apply {
        value = HawadeetAdapter(repository.provideHawadeet(selectedCategory))
    }

    fun buttonListener(previousButton: Button, it: Button) {
        previousButton.background = getDrawable(repository.app, R.drawable.button_unchecked)
        it.background = getDrawable(repository.app, R.drawable.button_checked)
        selectedCategory = if (it.text.toString() == "All") "" else it.text.toString()
        hawadeetAdapterLiveData.value =
            HawadeetAdapter(repository.provideHawadeet(selectedCategory))
    }

    fun addNewHadoota() {
        repository.app.startActivity(
            Intent(
                repository.app,
                NewHadoota::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }


}