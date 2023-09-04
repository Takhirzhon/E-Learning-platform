package com.tokhirzhon.learn.activity

import FreeCoursesFragment
import PremiumCoursesFragment
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.databinding.ActivityMenuBinding
import com.tokhirzhon.learn.ui.connect.FragmentConnect
import com.tokhirzhon.learn.ui.favourite.FavouriteFragment
import com.tokhirzhon.learn.ui.home.HomeFragment
import com.tokhirzhon.learn.ui.schedule.Schedule

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dashboard = findViewById<ImageView>(R.id.dashboard_btn)
        val containerCardView = findViewById<CardView>(R.id.cardview_dashboard)
        val personalMenu = findViewById<ImageButton>(R.id.personal_menu)
        val dashboardHome = findViewById<ImageButton>(R.id.home_menu)
        val freeCourses = findViewById<ImageButton>(R.id.free_courses_menu)
        val premiumCourses = findViewById<ImageButton>(R.id.premium_menu)
        val favouriteMenu = findViewById<ImageButton>(R.id.favourite_menu)
        val scheduleMenu = findViewById<ImageButton>(R.id.schedule_menu)

        dashboard.setOnClickListener {
            if (containerCardView.visibility == View.VISIBLE) {
                containerCardView.visibility = View.GONE
            } else {
                containerCardView.visibility = View.VISIBLE
            }
        }

        dashboardHome.setOnClickListener {
            containerCardView.visibility = View.GONE
            switchFragment(HomeFragment())
        }
        freeCourses.setOnClickListener {
            containerCardView.visibility = View.GONE
            switchFragment(FreeCoursesFragment())
        }
        premiumCourses.setOnClickListener {
            containerCardView.visibility = View.GONE
            switchFragment(PremiumCoursesFragment())
        }
        favouriteMenu.setOnClickListener {
            containerCardView.visibility = View.GONE
            switchFragment(FavouriteFragment())
        }

        scheduleMenu.setOnClickListener {
            containerCardView.visibility = View.GONE
            switchFragment(Schedule())
        }


        val coursesFreeBtn = findViewById<ImageButton>(R.id.coursesFree)
        val cardViewFree = findViewById<CardView>(R.id.coursesCardViewFree)
        coursesFreeBtn.setOnClickListener {
            if(cardViewFree.visibility == View.VISIBLE) {
                cardViewFree.visibility = View.GONE
            } else {
                cardViewFree.visibility = View.VISIBLE
            }
        }

        val coursesPremiumBtn = findViewById<ImageButton>(R.id.premium)
        val cardViewPremium = findViewById<CardView>(R.id.premiumCoursesView)

        coursesPremiumBtn.setOnClickListener{
            if(cardViewPremium.visibility == View.VISIBLE) {
                cardViewPremium.visibility = View.GONE
            } else {
                cardViewPremium.visibility = View.VISIBLE
            }
        }



        // Set the first fragment as the default fragment
        switchFragment(HomeFragment())
        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> switchFragment(HomeFragment())
                R.id.navigation_favourite -> switchFragment(FavouriteFragment())
                R.id.navigatioon_schedule -> switchFragment(Schedule())
                R.id.navigation_connect -> switchFragment(FragmentConnect())

                else -> false
            }
        }
    }

    private fun switchFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_menu, fragment)
            .commit()
        return true
    }
}
