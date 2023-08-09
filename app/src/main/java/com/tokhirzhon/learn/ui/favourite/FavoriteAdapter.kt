package com.tokhirzhon.learn.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.Course

class FavoriteAdapter(
    private var courses: List<Course>
) : RecyclerView.Adapter<FavoriteAdapter.CourseViewHolder>() {

    fun setCourses(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        // Inflate your item layout here
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favourite, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        // Bind course data to the views in your ViewHolder
        holder.bind(course)
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize your views here

        fun bind(course: Course) {
            // Bind course data to your views here
        }
    }
}
