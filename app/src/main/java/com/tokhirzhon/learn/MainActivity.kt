package com.tokhirzhon.learn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //Sign In button
        val button = findViewById<Button>(R.id.sign_btn)
        button.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        //register button
        val register = findViewById<TextView>(R.id.reg_txt)
        register.setOnClickListener{
            val intent = Intent(this, Register ::class.java)
            startActivity(intent)
            finish()
        }
    }
}