package com.tokhirzhon.learn.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.databinding.FragmentScheduleBinding
import com.tokhirzhon.learn.model.Course
import com.tokhirzhon.learn.view.Contract


class Schedule : Fragment(), Contract {

    private var sBinding: FragmentScheduleBinding? = null
    private val binding get() = sBinding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var courses: ArrayList<Course>
    private var database = Firebase.firestore
    private lateinit var scheduleAdapter: ScheduleAdapter

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

        database.collection("courses").get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val courses = ArrayList<Course>()
                for (document in querySnapshot) {
                    val course: Course = document.toObject(Course::class.java)
                    course.let { courses.add(it) }
                }
                scheduleAdapter.setCourses(courses) // Use setCourses method to update the data
            }
        }
            .addOnFailureListener {
                // Handle the failure case if needed
            }

        // Загрузите данные о курсах с сервера

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
