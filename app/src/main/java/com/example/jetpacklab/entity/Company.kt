package com.example.jetpacklab.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey
    var id: Int,
    var name: String
)