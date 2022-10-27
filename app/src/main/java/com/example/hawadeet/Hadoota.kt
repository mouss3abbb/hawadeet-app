package com.example.hawadeet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hadoota(
    @PrimaryKey(autoGenerate = false)
    val body: String,
    @ColumnInfo val status: String
)