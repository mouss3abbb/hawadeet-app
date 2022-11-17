package com.example.hawadeet.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModel
import com.example.hawadeet.Hadoota
import com.example.hawadeet.MainActivity
import com.example.hawadeet.R
import com.example.hawadeet.adapters.HawadeetAdapter
import com.example.hawadeet.api
import com.example.hawadeet.api.HawadeetApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewHadootaViewModel @SuppressLint("StaticFieldLeak") constructor(
    @SuppressLint("StaticFieldLeak") val context: Context,
    val view: View
): ViewModel() {
    private var selectedCategory = "Other"
    @SuppressLint("StaticFieldLeak")
    private var previousButton: Button = view.findViewById(R.id.other)


    fun addNewHadoota(view: View){
        val it = view as AppCompatEditText
        if(TextUtils.isEmpty(it.text.toString())){
            Toast.makeText(context,"Please provide text for your hadoota",Toast.LENGTH_SHORT)
                .show()
        }else{
            val addHadootaCall = api.addHadoota(Hadoota(it.text.toString(),selectedCategory))
            addHadootaCall.enqueue(object : Callback<Hadoota>{
                override fun onResponse(call: Call<Hadoota>, response: Response<Hadoota>) {
                    Toast.makeText(context,"Hadoota posted succefully",Toast.LENGTH_SHORT)
                        .show()
                }
                override fun onFailure(call: Call<Hadoota>, t: Throwable) {
                    Toast.makeText(context,"Network error",Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
        context.startActivity(
            Intent(
                context,
                MainActivity::class.java
            )
        )
    }
    fun buttonListener(view: View){
        val it = view as Button
        previousButton.background = getDrawable(context, R.drawable.button_unchecked)
        it.background = getDrawable(context, R.drawable.button_checked)
        previousButton = it
        selectedCategory = it.text.toString()
    }

}