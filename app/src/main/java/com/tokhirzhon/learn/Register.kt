package com.tokhirzhon.learn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView

class Register : ComponentActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        val contin = findViewById<Button>(R.id.sign_btn)
        val cards = findViewById<CardView>(R.id.additionalFieldsCardView)
        contin.setOnClickListener {
            cards.visibility = View.VISIBLE
            contin.visibility = View.GONE

        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@Register, Login::class.java)
                startActivity(intent)
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}