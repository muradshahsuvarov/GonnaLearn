package com.example.gonnalearn.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val fullName: String,
    val dateOfBirth: String,
    val role: String,
    val password: String
)