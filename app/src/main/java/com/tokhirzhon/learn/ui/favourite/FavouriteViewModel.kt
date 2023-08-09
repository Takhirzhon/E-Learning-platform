package com.tokhirzhon.learn.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.model.Course

class FavouriteViewModel : ViewModel() {
    private val favoriteCourses = MutableLiveData<List<Course>>()

    fun addFavoriteCourse(course: Course) {
        val db = Firebase.firestore

        val currentCourses = favoriteCourses.value?.toMutableList() ?: mutableListOf()
        currentCourses.add(course)
        favoriteCourses.value = currentCourses

        // Save the favorite course in Firebase Firestore
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val documentRefFavorite = db.collection("course").document(user.uid)
            documentRefFavorite.set(course)
                .addOnSuccessListener {
                    // Handle success
                }
                .addOnFailureListener {
                    // Handle failure
                }
        }
    }

    fun getFavoriteCourses(): LiveData<List<Course>> {
        return favoriteCourses
    }
}