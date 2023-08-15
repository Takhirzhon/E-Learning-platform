package com.tokhirzhon.learn.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.databinding.FragmentScheduleBinding
import com.tokhirzhon.learn.model.Course
import com.tokhirzhon.learn.model.SharedViewModel

class Schedule : Fragment() {

    private var sBinding: FragmentScheduleBinding? = null
    private val binding get() = sBinding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var courses: ArrayList<Course>
    private val database = Firebase.firestore
    private lateinit var courseAdapter: CourseAdapter
    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sBinding = FragmentScheduleBinding.inflate(inflater, container, false)

        val view = binding.root

        recyclerView = binding.courseRecyclerView
        courses = arrayListOf()

        courseAdapter = CourseAdapter(courses, sharedViewModel) { clickedCourse ->
            sharedViewModel.addToFavorites(clickedCourse as Course)
        }
        recyclerView.adapter = courseAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        database.collection("courses").get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                val loadedCourses = ArrayList<Course>()
                for (document in querySnapshot) {
                    val course: Course = document.toObject(Course::class.java)
                    loadedCourses.add(course)
                }
                courses.clear()
                courses.addAll(loadedCourses)
                courseAdapter.notifyDataSetChanged()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sBinding = null
    }
}
