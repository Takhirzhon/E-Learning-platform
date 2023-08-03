package com.tokhirzhon.learn.ui.schedule

import CourseAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.Course
import com.tokhirzhon.learn.view.Contract


class Schedule : Fragment(), Contract {

    private val presenter: SchedulePresenter by lazy { SchedulePresenter(this) }
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.courseRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        courseAdapter = CourseAdapter()
        recyclerView.adapter = courseAdapter

        // Загрузите данные о курсах с сервера
        presenter.getCoursesFromServer()

        return view
    }

    override fun showCourses(courses: MutableList<Course>) {
        // Обновите адаптер с полученными данными о курсах
        courseAdapter.setCourses(courses)
    }

    override fun showError(errorMessage: String) {
        // Отобразите сообщение об ошибке, если требуется
    }

    class SchedulePresenter(private val view: Contract) {

        private val coursesList: MutableList<Course> = mutableListOf()

        // Здесь должна быть логика для загрузки данных о курсах из сервера и обновление coursesList
        // Это может быть выполнено с использованием Firebase Realtime Database

        // Метод для получения данных о курсах
        fun getCoursesFromServer() {
            // Загрузка данных из Firebase
            // coursesList = ...
            // Обновление представления
            view.showCourses(coursesList)
        }
    }

}
