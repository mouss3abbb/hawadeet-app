package com.example.hawadeet.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModel
import com.example.hawadeet.Hadoota
import com.example.hawadeet.MainActivity
import com.example.hawadeet.R
import com.example.hawadeet.api.HawadeetApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewHadootaViewModel @Inject constructor(
    val app: Application,
    val api: HawadeetApi
) : ViewModel() {
    private var selectedCategory = "Other"

    fun addNewHadoota(view: View) {
        val it = view as AppCompatEditText
        if (TextUtils.isEmpty(it.text.toString().trim(' ', 'ْ', 'ﹾ'))) {
            Toast.makeText(app, "Please provide text for your hadoota", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(app, "Hadoota posted successfully", Toast.LENGTH_SHORT)
                .show()
            GlobalScope.launch(Dispatchers.IO) {
                val addHadootaResponse =
                    api.addHadoota(Hadoota(it.text.toString(), selectedCategory))
                if (!addHadootaResponse.isSuccessful) {
                    Toast.makeText(app, "Network Error", Toast.LENGTH_SHORT).show()
                }
            }
            app.startActivity(
                Intent(
                    app,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

    }

    fun buttonListener(previousButton: Button, it: Button) {
        previousButton.background = getDrawable(app, R.drawable.button_unchecked)
        it.background = getDrawable(app, R.drawable.button_checked)
        selectedCategory = it.text.toString()
    }

}