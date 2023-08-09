package com.tokhirzhon.learn.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {
    private lateinit var favoriteCoursesViewModel: FavouriteViewModel
    private lateinit var favoriteRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteCoursesViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        val binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoriteRecyclerView = binding.courseRecyclerView
        val favoriteAdapter = FavoriteAdapter(emptyList()) // Initialize with an empty list
        favoriteRecyclerView.adapter = favoriteAdapter

        favoriteCoursesViewModel.getFavoriteCourses().observe(viewLifecycleOwner) { courses ->
            favoriteAdapter.setCourses(courses)
        }

        return root
    }
}
