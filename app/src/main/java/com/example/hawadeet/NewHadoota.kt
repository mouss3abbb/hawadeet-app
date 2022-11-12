package com.example.hawadeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_new_hadoota.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewHadoota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_hadoota)

//        var checkedButton = "Other"
//        happy.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = happy.text.toString()
//        }
//        sad.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = sad.text.toString()
//        }
//        motivational.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = motivational.text.toString()
//        }
//        other.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = other.text.toString()
//        }
//        bored.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = bored.text.toString()
//        }
//        educational.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = educational.text.toString()
//        }
//        achievement.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = achievement.text.toString()
//        }
//        fun_button.setOnClickListener {
//            resetButtons()
//            it.background = getDrawable(R.drawable.button_checked)
//            checkedButton = fun_button.text.toString()
//        }
//
//        added_hadoota.setOnClickListener {
//            val hadoota = Hadoota(
//                new_hadoota.text.toString(),
//                checkedButton
//            )
//
//            if(TextUtils.isEmpty(hadoota.body)) {
//                val addHadootaCall = api.addHadoota(hadoota)
//                addHadootaCall.enqueue(object : Callback<Hadoota> {
//                    override fun onResponse(call: Call<Hadoota>, response: Response<Hadoota>) {
//                        Toast.makeText(
//                            applicationContext,
//                            "Hadoota posted successfully",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        val doneIntent = Intent(applicationContext, MainActivity::class.java)
//                        doneIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        startActivity(doneIntent)
//                    }
//
//                    override fun onFailure(call: Call<Hadoota>, t: Throwable) {
//                        Toast.makeText(applicationContext, "An error occured", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                })
//            }else{
//                Toast.makeText(applicationContext,"Please write your hadoota",Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    fun resetButtons(){
        happy.background = getDrawable(R.drawable.button_unchecked)
        sad.background = getDrawable(R.drawable.button_unchecked)
        motivational.background = getDrawable(R.drawable.button_unchecked)
        bored.background = getDrawable(R.drawable.button_unchecked)
        educational.background = getDrawable(R.drawable.button_unchecked)
        fun_button.background = getDrawable(R.drawable.button_unchecked)
        achievement.background = getDrawable(R.drawable.button_unchecked)
        other.background = getDrawable(R.drawable.button_unchecked)
    }
}