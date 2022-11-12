package com.example.hawadeet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hawadeet.Hadoota

@Database(entities = [Hadoota::class], version = 1)
abstract class HawadeetDatabase : RoomDatabase(){
    abstract fun hadootaDao(): HadootaDao

    companion object{
        @Volatile private var INSTANCE: HawadeetDatabase? = null

        fun getInstance(context: Context): HawadeetDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                    context,
                    HawadeetDatabase::class.java,
                    "hawadeet_db"
                ).build()
            INSTANCE = instance
            instance
        }
    }
}