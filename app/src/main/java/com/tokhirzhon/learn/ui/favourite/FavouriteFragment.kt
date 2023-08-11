package com.tokhirzhon.learn.ui.favourite

import FavoriteCourseAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.databinding.FragmentFavouriteBinding
import com.tokhirzhon.learn.model.SharedViewModel
import com.tokhirzhon.learn.model.Course

class FavouriteFragment : Fragment() {

    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoriteRecyclerView = binding.favouriteRecyclerView

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val favoriteAdapter = FavoriteCourseAdapter(emptyList())
        favoriteRecyclerView.adapter = favoriteAdapter
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        sharedViewModel.favoriteCourses.observe(viewLifecycleOwner) { favoriteCourses ->
            favoriteAdapter.updateCourses(favoriteCourses)
        }

        return root
    }
}
