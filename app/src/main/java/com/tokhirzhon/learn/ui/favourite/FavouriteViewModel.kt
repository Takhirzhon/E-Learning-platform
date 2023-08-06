package com.tokhirzhon.learn.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokhirzhon.learn.model.Course

class FavouriteViewModel : ViewModel() {
    private val _favoriteCourses = MutableLiveData<List<Course>>()
    val favoriteCourses: LiveData<List<Course>> = _favoriteCourses

    fun addFavoriteCourse(course: Course) {
        val currentList = _favoriteCourses.value ?: emptyList()
        _favoriteCourses.value = currentList + course
    }
}