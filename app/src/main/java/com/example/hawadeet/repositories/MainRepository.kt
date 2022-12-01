package com.example.hawadeet.repositories

import android.app.Application
import com.example.hawadeet.Hadoota
import com.example.hawadeet.api.HawadeetApi
import com.example.hawadeet.db.HadootaDao
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


class MainRepository @Inject constructor(
    val api: HawadeetApi,
    private val dao: HadootaDao,
    val app: Application,
    private val NO_HAWADEET: Hadoota
) {

    init {
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertHawadeet(api.getHawadeet().body()!!)
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