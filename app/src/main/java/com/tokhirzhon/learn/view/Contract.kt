package com.tokhirzhon.learn.view

import com.tokhirzhon.learn.model.Course

interface Contract {
    fun showCourses(courses: MutableList<Course>)
    fun showError(errorMessage: String)
}

class Courses {

}
