package com.tokhirzhon.learn.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _favoriteCourses = MutableLiveData<List<Course>>()
    val favoriteCourses: LiveData<List<Course>> get() = _favoriteCourses

    fun addToFavorites(course: Course) {
        val updatedList = _favoriteCourses.value?.toMutableList() ?: mutableListOf()
        updatedList.add(course)
        _favoriteCourses.value = updatedList
    }
}
