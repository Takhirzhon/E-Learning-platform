package com.tokhirzhon.learn.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.databinding.FragmentScheduleBinding
import com.tokhirzhon.learn.model.Course
import com.tokhirzhon.learn.view.Contract


class Schedule : Fragment(), Contract {

    private var sBinding: FragmentScheduleBinding? = null
    private val binding get() = sBinding!!
    private lateinit var recyclerView: RecyclerView
    private val presenter: SchedulePresenter by lazy { SchedulePresenter(this) }
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var courses: ArrayList<Course>
    private var database = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sBinding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        // Initialize the RecyclerView and Adapter
        recyclerView = binding.courseRecyclerView

        courses = arrayListOf() // Initialize the courses list first
        scheduleAdapter = ScheduleAdapter(courses)
        recyclerView.adapter = scheduleAdapter

        database = FirebaseFirestore.getInstance()
        database.collection("courses").get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot) {
                    val course: Course? = document.toObject(Course::class.java)
                    course?.let { courses.add(it) }
                }
                scheduleAdapter.setCourses(courses) // Use setCourses method to update the data
            }
        }
            .addOnFailureListener { exception ->
                // Handle the failure case if needed
            }

        // Загрузите данные о курсах с сервера
        presenter.getCoursesFromServer()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sBinding = null
    }

    override fun showCourses(courses: ArrayList<Course>) {
        // Обновите адаптер с полученными данными о курсах
        scheduleAdapter.setCourses(courses)
    }

    override fun showError(errorMessage: String) {
        // Отобразите сообщение об ошибке, если требуется
    }

    private fun init() {
    }
}

class SchedulePresenter(private val view: Contract) {

    //private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    // private val coursesCollection = db.collection("courses")

    private val coursesList: ArrayList<Course> = arrayListOf()

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
