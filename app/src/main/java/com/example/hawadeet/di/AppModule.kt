package com.example.hawadeet.di

import android.app.Application
import com.example.hawadeet.Hadoota
import com.example.hawadeet.MainActivity
import com.example.hawadeet.api.HawadeetApi
import com.example.hawadeet.db.HadootaDao
import com.example.hawadeet.db.HawadeetDatabase
import com.example.hawadeet.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = "https://hawadeet-api.herokuapp.com/"

    @Provides
    @Singleton
    fun provideEmptyHawadeetList() = Hadoota("No hawadeet found", "")

    @Provides
    @Singleton
    fun provideHawadeetApi(url: String): HawadeetApi = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HawadeetApi::class.java)

    @Provides
    @Singleton
    fun provideHawadeetDao(app: Application): HadootaDao =
        HawadeetDatabase.getInstance(app).hadootaDao()

    @Provides
    @Singleton
    fun provideMainRepository(
        api: HawadeetApi,
        dao: HadootaDao,
        app: Application,
        NO_HAWADEET: Hadoota
    ): MainRepository {
        return MainRepository(api, dao,app,NO_HAWADEET)
    }

}