package com.tokhirzhon.learn.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tokhirzhon.learn.model.Course

class FavouriteViewModel : ViewModel() {
    private val favoriteCourses = MutableLiveData<List<Course>>()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun addFavoriteCourse(course: Course) {
        val currentCourses = favoriteCourses.value?.toMutableList() ?: mutableListOf()
        currentCourses.add(course)
        favoriteCourses.value = currentCourses

        val user = FirebaseAuth.getInstance().currentUser
        user?.uid?.let { userId ->
            val userFavoritesRef = database.child("user_favorites").child(userId)
            val courseId = userFavoritesRef.push().key // Generate a new key for each course
            if (courseId != null) {
                userFavoritesRef.child(courseId).setValue(course)
                    .addOnSuccessListener {
                        // Handle success
                    }
                    .addOnFailureListener {
                        // Handle failure
                    }
            }
        }
    }

    fun getFavoriteCourses(): LiveData<List<Course>> {
        return favoriteCourses
    }
}
