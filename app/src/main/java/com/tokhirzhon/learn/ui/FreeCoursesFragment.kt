import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tokhirzhon.learn.R

class FreeCoursesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Здесь разместите логику и макет для фрагмента бесплатных курсов
        return inflater.inflate(R.layout.fragment_freecourses, container, false)
    }
}
