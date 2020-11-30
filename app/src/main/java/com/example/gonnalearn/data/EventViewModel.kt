package com.example.gonnalearn.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// is needed to provide data to the UI
class EventViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Event>>
    val repository: EventRepository

    init{
        val eventDao = UserDatabase.getDatabase(application).eventDao()
        repository = EventRepository(eventDao)
        readAllData = repository.readAllData
    }

    fun addEvent(event: Event){
        viewModelScope.launch(Dispatchers.IO) {  // running corouting running in the background
            repository.addEvent(event)
        }
    }

    fun updateEvent(event : Event){
        viewModelScope.launch( Dispatchers.IO) {
            repository.updateEvent(event)
        } // running corouting running in the background
    }

}