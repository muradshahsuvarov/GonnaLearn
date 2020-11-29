package com.example.gonnalearn.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// is needed to provide data to the UI
class UserViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData: LiveData<List<User>>
     val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {  // running corouting running in the background
            repository.addUser(user)
        }
    }

}