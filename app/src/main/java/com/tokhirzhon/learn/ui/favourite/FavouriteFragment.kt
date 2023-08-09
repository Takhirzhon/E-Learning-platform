package com.tokhirzhon.learn.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {
    private lateinit var favoriteCoursesViewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteCoursesViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        val binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Observe the favorite courses and update the UI
        favoriteCoursesViewModel.getFavoriteCourses().observe(viewLifecycleOwner) { courses ->
            // Update the UI with the favorite courses
            // You can access the courses list and display them in the UI
            // For example, you can use a RecyclerView to display the favorite courses
        }

        return root
    }
}
