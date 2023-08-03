import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.Course

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private val courses: MutableList<Course> = mutableListOf()

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
        val course = courses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.courseTitleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.courseDescriptionTextView)
        private val startDateTextView = itemView.findViewById<TextView>(R.id.startDateTextView)
        private val contactButton: Button = itemView.findViewById(R.id.contactButton)

        @SuppressLint("SetTextI18n")
        fun bind(course: Course) {
            titleTextView.text = course.title
            descriptionTextView.text = course.description
            startDateTextView.text = "Start Date: ${course.startDate}"
            // Обработчик нажатия на кнопку "Связаться" здесь
        }
    }
}
