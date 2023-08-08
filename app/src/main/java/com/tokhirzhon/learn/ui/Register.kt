package com.tokhirzhon.learn.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.compose.material3.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.User

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var users: DatabaseReference


    @SuppressLint("CutPasteId")
    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        auth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance()
        users = dataBase.getReference("Users")

        val contin = findViewById<Button>(R.id.contin)
        val cards = findViewById<CardView>(R.id.additionalFieldsCardView)

        contin.setOnClickListener {
            cards.visibility = View.VISIBLE
            contin.visibility = View.GONE

        }

        val signUpIn = findViewById<Button>(R.id.sign)
        signUpIn.setOnClickListener {
            signUp()
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


            auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                .addOnSuccessListener {
                    val user = User()
                    user.setEmail(email.toString())
                    user.setPassword(password.toString())
                    user.setCityName(cityName.toString())
                    user.setSchoolName(schoolName.toString())

                    users.child(user.getEmail()).setValue(user).addOnSuccessListener {
                        Toast.makeText(this, "Вы добавлены", Toast.LENGTH_SHORT).show()
                }
        }
    }
}