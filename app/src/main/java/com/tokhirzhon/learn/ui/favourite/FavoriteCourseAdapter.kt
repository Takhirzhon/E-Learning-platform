import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.databinding.ItemFavouriteBinding
import com.tokhirzhon.learn.databinding.ItemScheduleBinding
import com.tokhirzhon.learn.model.Course

class FavoriteCourseAdapter(private var favoriteCourses: List<Course>) :
    RecyclerView.Adapter<FavoriteCourseAdapter.CourseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(
            inflater,
            parent,
            false
        )
        return CourseViewHolder(binding.root)
    }

    fun removeCourse(course: Course) {
        val updatedList = favoriteCourses.toMutableList()
        updatedList.remove(course)
        favoriteCourses = updatedList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = favoriteCourses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = favoriteCourses.size

    fun updateCourses(newCourses: List<Course>) {
        favoriteCourses = newCourses
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemScheduleBinding.bind(itemView)

        fun bind(course: Course) {
            binding.courseTitleTextView.text = course.title
            binding.courseDescriptionTextView.text = course.description
            binding.startDateMonth.text = course.startDateMonth
            binding.startDateValue.text = course.startDate
            binding.costCourse.text = course.costCourse

            binding.favouriteAdd.setOnClickListener {
                removeCourse(course)
            }
        }
    }
}
