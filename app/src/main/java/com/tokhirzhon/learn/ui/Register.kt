package com.tokhirzhon.learn.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.User

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dataBase: DatabaseReference
    private val USER_KEY : String = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        auth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)

        val contin = findViewById<Button>(R.id.contin)
        val cards = findViewById<CardView>(R.id.additionalFieldsCardView)

        contin.setOnClickListener {
            cards.visibility = View.VISIBLE
            contin.visibility = View.GONE
        }

        val signUpIn = findViewById<Button>(R.id.sign)
        signUpIn.setOnClickListener {
            signUp()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
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
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
        val passwordSure = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()
        val cityName = findViewById<EditText>(R.id.city_name).text.toString()
        val schoolName = findViewById<EditText>(R.id.school_name).text.toString()
        val schoolNumber = findViewById<EditText>(R.id.school_num).text.toString()
        val classNum = findViewById<EditText>(R.id.class_num).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && password == passwordSure) {
            val user = User(email, password, cityName, schoolName, schoolNumber, classNum)
            dataBase.push().setValue(user)
        }
    }
}