package com.tokhirzhon.learn.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import com.tokhirzhon.learn.R

class Login : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_layout)

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
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}
