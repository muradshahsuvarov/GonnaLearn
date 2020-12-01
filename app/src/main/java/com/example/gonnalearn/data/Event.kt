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
        var id: Int,
        var title : String,
        var description : String,
        var start_date : String,
        var end_date : String,
        var userId : Int,
        var subscriberEmail : String,
        var status : String

    ) : Parcelable