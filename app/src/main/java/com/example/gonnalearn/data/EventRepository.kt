package com.example.gonnalearn.data

import androidx.lifecycle.LiveData

// Class that abstracts access to multiple data source.

class EventRepository(private val eventDao: EventDao) {

    val readAllData: LiveData<List<Event>> = eventDao.readAllData()

    suspend fun addEvent(event: Event){
        eventDao.addEvent(event)
    }

    suspend fun updateEvent(event : Event){
        eventDao.updateEvent(event)
    }
}