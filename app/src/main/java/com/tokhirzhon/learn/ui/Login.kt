package com.tokhirzhon.learn.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tokhirzhon.learn.R

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_layout)
        auth = FirebaseAuth.getInstance()


        val cardViewReset = findViewById<CardView>(R.id.resetCardView) //Cardview сброс пароля
        val rememebered = findViewById<TextView>(R.id.remembered) // Вспомнил пароль назад
        val forgotPassword = findViewById<TextView>(R.id.forgotPassword) //Забыл пароль для сброса
        val signInAcc = findViewById<Button>(R.id.sign_btn) //Войти

        cardViewReset.setOnClickListener {}


        forgotPassword.setOnClickListener {
            cardViewReset.visibility = View.VISIBLE
            forgotPassword.visibility = View.GONE
        }

        rememebered.setOnClickListener {
            forgotPassword.visibility = View.VISIBLE
            cardViewReset.visibility = View.GONE
        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@Login, MainActivity::class.java)
                startActivity(intent)
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

        signInAcc.setOnClickListener {
            validateData()
        }
    }


    private fun validateData() {
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString().trim()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString().trim()

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            val firebaseUser = auth.currentUser!!

            val ref = FirebaseDatabase.getInstance().getReference("users")
            ref.child(firebaseUser.uid)
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }
            .addOnFailureListener{
                Toast.makeText(this, "Что то пошло не так", Toast.LENGTH_LONG).show()
            }
    }
}


