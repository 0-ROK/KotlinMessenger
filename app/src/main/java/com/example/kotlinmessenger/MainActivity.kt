package com.example.kotlinmessenger

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectPhotoRegisterButton : Button = findViewById(R.id.select_photo_button_register)

        val userNameEditText : EditText = findViewById(R.id.username_edittext_register)
        val emailEditText : EditText = findViewById(R.id.email_edittext_register)
        val passwordEditText : EditText = findViewById(R.id.password_edittext_register)
        val registerButton : Button = findViewById(R.id.register_button)

        val alreadyTextView : TextView = findViewById(R.id.already_have_account_text_view)


        selectPhotoRegisterButton.setOnClickListener {
            Log.d("MainActivities", "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }



//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode, resultCode, data)
//
//            if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
//                Log.d("RegisterActivity", "photo was selected")
//            }
//
//            val uri = data.data
//
//            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
//        }


        registerButton.setOnClickListener {
//            performRegister()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter text in email/pw.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("MainActivity", "Email is : " + email)
            Log.d("MainActivity", "Password : $password")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Main", "Successfully created user with uid ${it.result.user?.uid}")
                }
                .addOnFailureListener{
                    Log.d("Main", "Failed to create user : ${it.message}")
                }
        }

        alreadyTextView.setOnClickListener {
            Log.d("MainActivity", "Try to show login activity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


//    private fun uploadImageToFirebaseStorage() {
//        if (selectedPhotoUri == null) return
//
//        val filename = UUID.randomUUID().toString()
//        val ref = FirebaseStorage.getInstance().getReference("/images/")
//
//        ref.putFile(selectedPhotoUri!!)
//                .addOnSuccessListener {
//                    Log.d("Register", "successfully uploaded Images ${it.metadata?.path}")
//                    ref.downloadUrl.addOnSuccessListener {
//                        it.toString()
//                        Log.d("Register", "file locateion $it")
//                    }
//                }
//
//
//    }

//    private fun performRegister() {
//        val email = emailEditText.text.toString()
//        val password = passwordEditText.text.toString()
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Please enter text in email/pw.", Toast.LENGTH_SHORT).show()
//            return@setOnClickListener
//        }
//
//        Log.d("MainActivity", "Email is : " + email)
//        Log.d("MainActivity", "Password : $password")
//
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                if (!it.isSuccessful) return@addOnCompleteListener
//
//                Log.d("Main", "Successfully created user with uid ${it.result.user?.uid}")
//            }
//            .addOnFailureListener{
//                Log.d("Main", "Failed to create user : ${it.message}")
//            }
//    }
}