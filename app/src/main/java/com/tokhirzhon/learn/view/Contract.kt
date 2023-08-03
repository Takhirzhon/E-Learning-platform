package com.tokhirzhon.learn.view

import com.tokhirzhon.learn.model.Course


interface Contract {
    fun showCourses(courses: ArrayList<Course>)
    fun showError(errorMessage: String)
}