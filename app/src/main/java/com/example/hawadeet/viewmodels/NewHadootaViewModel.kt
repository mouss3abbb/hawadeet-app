package com.example.hawadeet.viewmodels

import android.annotation.SuppressLint
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
import com.example.hawadeet.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewHadootaViewModel @SuppressLint("StaticFieldLeak") constructor(
    @SuppressLint("StaticFieldLeak") val context: Context,
    val view: View
) : ViewModel() {
    private var selectedCategory = "Other"

    @SuppressLint("StaticFieldLeak")
    private var previousButton: Button = view.findViewById(R.id.other)


    fun addNewHadoota(view: View) {
        val it = view as AppCompatEditText
        if (TextUtils.isEmpty(it.text.toString().trim())) {
            Toast.makeText(context, "Please provide text for your hadoota", Toast.LENGTH_SHORT)
                .show()
        } else {
            GlobalScope.launch (Dispatchers.IO){
                val addHadootaResponse = api.addHadoota(Hadoota(it.text.toString(), selectedCategory))
                if (!addHadootaResponse.isSuccessful) {
                    Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
                }
            }
            context.startActivity(
                Intent(
                    context,
                    MainActivity::class.java
                )
            )
        }

    }

    @SuppressLint("ResourceAsColor")
    fun buttonListener(view: View) {
        val it = view as Button
        previousButton.background = getDrawable(context, R.drawable.button_unchecked)
        it.background = getDrawable(context, R.drawable.button_checked)
        previousButton = it
        selectedCategory = it.text.toString()
    }

}