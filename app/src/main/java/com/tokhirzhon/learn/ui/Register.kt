package com.tokhirzhon.learn.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
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
import com.google.firebase.ktx.Firebase
import com.tokhirzhon.learn.R
import com.tokhirzhon.learn.model.User

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        auth = Firebase.auth

        auth = FirebaseAuth.getInstance()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    account.idToken?.let { it1 -> firebaseAuthWithGoogle(it1) }
                }
            }catch (_: ApiException) {

            }
        }
        val signInGoogleWrapper = findViewById<View>(R.id.google_sing_in_register_wrapper)
        signInGoogleWrapper.setOnClickListener{
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
            val user = User(email, password, cityName, schoolName, schoolNumber, classNum)

            auth.createUserWithEmailAndPassword(email, password)
        }
    }
    private fun getClient() : GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return  GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                Log.d("My Log", "Вы вошли в аккаунт")
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            else {
                Log.d("My Log", "Ошибка входа, попробуйте еще раз!")
            }
        }
    }
}