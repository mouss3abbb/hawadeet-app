package com.example.hawadeet.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.example.hawadeet.Hadoota
import com.example.hawadeet.NO_HAWADEET
import com.example.hawadeet.api
import com.example.hawadeet.api.HawadeetApi
import com.example.hawadeet.db.HawadeetDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository private constructor(
    val context: Context
) {

    private val dao = HawadeetDatabase.getInstance(context).hadootaDao()
    init {
        GlobalScope.launch (Dispatchers.IO){
            dao.insertHawadeet(api.getHawadeet().body()!!)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MainRepository? = null
        fun getInstance(context: Context): MainRepository {
            return INSTANCE ?: synchronized(this) { MainRepository(context) }
        }
    }

    fun provideHawadeet(selectedCategory: String): List<Hadoota> {
        var adapterList: List<Hadoota>
        runBlocking {
            adapterList = if (selectedCategory.isEmpty())
                dao.getHawadeet()
            else dao.getHawadeet(selectedCategory)
            if (adapterList.isEmpty())
                adapterList = listOf(NO_HAWADEET)
        }
        return adapterList.reversed()
    }
}