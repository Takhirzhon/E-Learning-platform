package com.tokhirzhon.learn.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.User
import kotlin.jvm.internal.Intrinsics.Kotlin

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordSureEditText: EditText
    private lateinit var cityNameEditText: EditText
    private lateinit var schoolNameEditText: EditText
    private lateinit var schoolNumberEditText: EditText
    private lateinit var classNumEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        auth = Firebase.auth
        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        passwordSureEditText = findViewById(R.id.editTextTextPassword2)
        cityNameEditText = findViewById(R.id.city_name)
        schoolNameEditText = findViewById(R.id.school_name)
        schoolNumberEditText = findViewById(R.id.school_num)
        classNumEditText = findViewById(R.id.class_num)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    account.idToken?.let { it1 -> firebaseAuthWithGoogle(it1) }
                }
            } catch (_: ApiException) {

            }
        }
        val signInGoogleWrapper = findViewById<View>(R.id.google_sing_in_register_wrapper)
        signInGoogleWrapper.setOnClickListener {
            signInWithGoogle()
        }
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
        //кнопка назад
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

    //кнопка конец


    private fun signUp() {
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
        val passwordSure = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()
        val cityName = findViewById<EditText>(R.id.city_name).text.toString()
        val schoolName = findViewById<EditText>(R.id.school_name).text.toString()
        val schoolNumber = findViewById<EditText>(R.id.school_num).text.toString()
        val classNum = findViewById<EditText>(R.id.class_num).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && password == passwordSure) {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val timeStamp = System.currentTimeMillis()
                val uid = auth.uid

                val hashMap: HashMap<String, Any?> = HashMap()
                hashMap["uid"] = uid
                hashMap["email"] = email
                hashMap["cityName"] = cityName
                hashMap["schoolName"] = schoolName
                hashMap["schoolNum"] = schoolNumber
                hashMap["classNum"] = classNum
                hashMap["timeStamp"] = timeStamp

                val ref = FirebaseDatabase.getInstance().getReference("users")
                ref.child(uid!!).setValue(hashMap)
                    .addOnSuccessListener {
                        val intent = Intent(this, MenuActivity::class.java)
                        startActivity(intent)
                    }
            }
        }
    }


    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }

        }
    }
}