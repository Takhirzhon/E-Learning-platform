package com.tokhirzhon.learn.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.databinding.ItemFavouriteBinding // Change to your actual binding class
import com.tokhirzhon.learn.model.Course

class CourseAdapter(
    private val courses: List<Course>,
    private val onFavouriteClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemFavouriteBinding.inflate(
            inflater,
            parent,
            false
        ) // Change to your actual binding class
        return CourseViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        // Bind your course data to the view holder's views here
        holder.bind(course)
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavouriteBinding.bind(itemView)

        fun bind(course: Course) {
            binding.courseTitleTextView.text = course.title
            binding.courseDescriptionTextView.text = course.description
            binding.startDateMonth.text = course.startDateMonth
            binding.startDateValue.text = course.startDate
            binding.costCourse.text = course.costCourse

            binding.favouriteAdd.setOnClickListener {
                onFavouriteClick(course) // Invoke the callback when the button is clicked
            }
        }
    }
}
