package com.tokhirzhon.learn.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.databinding.FragmentScheduleBinding
import com.tokhirzhon.learn.model.Course
import com.tokhirzhon.learn.ui.favourite.FavouriteViewModel
import com.tokhirzhon.learn.view.Contract


class Schedule : Fragment(), Contract {
    private lateinit var favoriteCoursesViewModel: FavouriteViewModel

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
        favoriteCoursesViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        val view = binding.root

        // Initialize the RecyclerView and Adapter
        recyclerView = binding.courseRecyclerView
        courses = arrayListOf() // Initialize the courses list first
        scheduleAdapter = ScheduleAdapter(courses, favoriteCoursesViewModel)
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
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sBinding = null
    }


}
