package com.tokhirzhon.learn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var coursesFreeBtn: ImageButton
    private lateinit var cardViewFree: CardView
    private lateinit var freeCoursesView: RecyclerView
    private lateinit var coursesPremiumBtn: ImageButton
    private lateinit var cardViewPremium: CardView
    private lateinit var premiumCoursesView: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Найдите кнопки и CardView элементы
        coursesFreeBtn = root.findViewById(R.id.coursesFree)
        cardViewFree = root.findViewById(R.id.coursesCardViewFree)
        freeCoursesView = root.findViewById(R.id.freeCoursesView)
        coursesPremiumBtn = root.findViewById(R.id.premium)
        cardViewPremium = root.findViewById(R.id.coursesCardViewPremium)
        premiumCoursesView = root.findViewById(R.id.premiumCoursesView)

        // Установите слушатели для кнопок
        coursesFreeBtn.setOnClickListener {
            if (cardViewFree.visibility == View.VISIBLE) {
                cardViewFree.visibility = View.GONE
            } else {
                cardViewFree.visibility = View.VISIBLE
            }
        }

        coursesPremiumBtn.setOnClickListener {
            if (cardViewPremium.visibility == View.VISIBLE) {
                cardViewPremium.visibility = View.GONE
            } else {
                cardViewPremium.visibility = View.VISIBLE
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
