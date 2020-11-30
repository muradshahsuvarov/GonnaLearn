package com.example.gonnalearn.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvent(event : Event)

    @Update
    suspend fun updateEvent(event : Event)

    @Query("SELECT * from events_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Event>>

}