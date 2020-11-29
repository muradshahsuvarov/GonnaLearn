package com.example.gonnalearn.data

import androidx.lifecycle.LiveData

// Class that abstracts access to multiple data source.

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}