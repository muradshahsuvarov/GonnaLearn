package com.example.gonnalearn.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
@Entity(tableName = "events_table")
data class Event (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title : String,
        val description : String,
        val start_date : String,
        val end_date : String,
        val userId : Int,
        val subscriberEmail : String,
        val status : String

    ) : Parcelable