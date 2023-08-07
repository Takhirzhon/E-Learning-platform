package com.tokhirzhon.learn.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tokhirzhon.learn.R

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
     var dataBase = FirebaseDatabase


    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        val contin = findViewById<Button>(R.id.sign_btn)
        val cards = findViewById<CardView>(R.id.additionalFieldsCardView)

        contin.setOnClickListener {
            cards.visibility = View.VISIBLE
            contin.visibility = View.GONE

        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (cards.visibility == View.VISIBLE) {
                    cards.visibility = View.GONE
                    contin.visibility = View.VISIBLE
                } else {
                    val intent = Intent(this@Register, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

    }


    private fun signUp() {
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val passwordSure = findViewById<EditText>(R.id.editTextTextPassword2)
        val cityName = findViewById<EditText>(R.id.city_name)
        val schoolName = findViewById<EditText>(R.id.school_name)
        val schoolNumber = findViewById<EditText>(R.id.school_num)
        val classNum = findViewById<EditText>(R.id.class_num)

    }

    fun onClickSave(view: View){
        String email
    }

}