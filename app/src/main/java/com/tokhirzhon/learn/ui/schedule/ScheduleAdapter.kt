package com.tokhirzhon.learn.ui.schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.Course

class ScheduleAdapter(private val courses: ArrayList<Course>) :
    RecyclerView.Adapter<ScheduleAdapter.CourseViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setCourses(newCourses: List<Course>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.title.text = courses[position].title
        holder.description.text = courses[position].description
        holder.startDate.text = courses[position].startDate
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.courseTitleTextView)
        val description: TextView = itemView.findViewById(R.id.courseDescriptionTextView)
        val startDate = itemView.findViewById<TextView>(R.id.startDateTextView)
        val contactButton: Button = itemView.findViewById(R.id.contactButton)
    }
}
