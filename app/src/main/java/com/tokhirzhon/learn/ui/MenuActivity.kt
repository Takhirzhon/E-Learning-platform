package com.tokhirzhon.learn.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.databinding.ActivityMenuBinding
import com.tokhirzhon.learn.ui.connect.FragmentConnect
import com.tokhirzhon.learn.ui.favourite.FavouriteFragment
import com.tokhirzhon.learn.ui.home.HomeFragment
import com.tokhirzhon.learn.ui.personal.Personal
import com.tokhirzhon.learn.ui.schedule.Schedule
import com.tokhirzhon.learn.ui.dashboard.Dashboard

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dashboard = findViewById<ImageView>(R.id.dashboard_btn)
        val containerCardView = findViewById<CardView>(R.id.cardview_dashboard)
        dashboard.setOnClickListener{
            containerCardView.visibility = View.VISIBLE
            }


        // Set the first fragment as the default fragment
        switchFragment(HomeFragment())

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> switchFragment(HomeFragment())
                R.id.navigation_favourite -> switchFragment(FavouriteFragment())
                R.id.navigatioon_schedule -> switchFragment(Schedule())
                R.id.navigation_connect -> switchFragment(FragmentConnect())
                R.id.navigation_personal -> switchFragment(Personal())

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
