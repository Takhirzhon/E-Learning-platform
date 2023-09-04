package com.tokhirzhon.learn.ui.home

import FreeCoursesFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.activity.MainActivity
import com.tokhirzhon.learn.databinding.FragmentHomeBinding
import com.tokhirzhon.learn.ui.connect.FragmentConnect

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var coursesFreeBtn: ImageButton
    private lateinit var cardViewFree: CardView
    private lateinit var freeCoursesView: RecyclerView
    private lateinit var coursesPremiumBtn: ImageButton
    private lateinit var cardViewPremium: CardView
    private lateinit var premiumCoursesView: RecyclerView
    private lateinit var connectBtn: Button

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Найдите кнопки и CardView элементы
        connectBtn = root.findViewById(R.id.connect_connect)
        coursesFreeBtn = root.findViewById(R.id.coursesFree)
        cardViewFree = root.findViewById(R.id.coursesCardViewFree)
        freeCoursesView = root.findViewById(R.id.freeCoursesView)
        coursesPremiumBtn = root.findViewById(R.id.premium)
        cardViewPremium = root.findViewById(R.id.coursesCardViewPremium)
        premiumCoursesView = root.findViewById(R.id.premiumCoursesView)


        connectBtn.setOnClickListener {
            val connect = FragmentConnect()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.nav_host_fragment_activity_menu, connect)
            transaction.commit()
        }

        // Установите слушатели для кнопок
        coursesFreeBtn.setOnClickListener {
            if (cardViewFree.visibility == View.VISIBLE) {
                cardViewFree.visibility = View.GONE
            } else {
                val connect = FreeCoursesFragment()
                val transaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.nav_host_fragment_activity_menu, connect)
                transaction.commit()
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


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (cardViewFree.visibility == View.VISIBLE) {
                    cardViewFree.visibility = View.GONE


                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
