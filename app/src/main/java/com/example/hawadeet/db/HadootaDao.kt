package com.example.hawadeet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hawadeet.Hadoota

@Dao
interface HadootaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHawadeet(hawadeet: List<Hadoota>)

    @Query("SELECT * FROM Hadoota")
    suspend fun getHawadeet(): List<Hadoota>

    @Query("SELECT * FROM Hadoota WHERE status = :status")
    suspend fun getHawadeet(status: String): List<Hadoota>
}