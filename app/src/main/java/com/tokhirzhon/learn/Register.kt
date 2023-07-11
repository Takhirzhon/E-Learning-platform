package com.tokhirzhon.learn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.tokhirzhon.learn.R

class Register : ComponentActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        val contin = findViewById<Button>(R.id.sign_btn)
        val additionalFieldsCardView = findViewById<CardView>(R.id.additionalFieldsCardView)

        contin.setOnClickListener{
            val cards = findViewById<CardView>(R.id.additionalFieldsCardView)
            additionalFieldsCardView.visibility = View.VISIBLE
            contin.visibility = View.GONE

        }
    }
}