package com.example.kotlinmessenger

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login_button)
        val emailEditText : EditText = findViewById(R.id.email_edittext_register)
        val passwordEditText : EditText = findViewById(R.id.password_edittext_register)
        val backToRegisterTextView : TextView = findViewById(R.id.back_to_register_textview)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            Log.d("Login", "Attempt login with email/pw: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener()
        }

        backToRegisterTextView.setOnClickListener{
            finish()
        }





    }


}