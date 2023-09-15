package com.tokhirzhon.learn.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _favoriteCourses = MutableLiveData<List<Course>>()
    val favoriteCourses: LiveData<List<Course>> get() = _favoriteCourses


    fun addToFavorites(course: Course) {
        val currentFavorites = _favoriteCourses.value.orEmpty().toMutableList()
        if (!currentFavorites.contains(course)) {
            currentFavorites.add(course)
        }
        _favoriteCourses.value = currentFavorites
    }

    fun removeFromFavorites(course: Course) {
        val currentFavorites = _favoriteCourses.value.orEmpty().toMutableList()
        currentFavorites.remove(course)
        _favoriteCourses.value = currentFavorites
    }
}
