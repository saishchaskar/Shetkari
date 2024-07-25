package com.oneearth.shetkari

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class SignupActivity : AppCompatActivity() {
    private lateinit var signupName: EditText
    private lateinit var signupEmail: EditText
    private lateinit var signupPassword: EditText
    private lateinit var signupPassword2: EditText
    private lateinit var loginRedirectText: TextView
    private lateinit var signupButton: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    lateinit var tvRedirectLogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signupName = findViewById(R.id.signup_name)
        signupEmail = findViewById(R.id.signup_email)
        signupPassword2 = findViewById(R.id.signup_password2)
        signupPassword = findViewById(R.id.signup_password)
        signupButton = findViewById(R.id.signup_button)
        loginRedirectText = findViewById(R.id.loginRedirectText)
        tvRedirectLogin = findViewById(R.id.loginRedirectText)
        auth = Firebase.auth

        signupButton.setOnClickListener {
            signUpUser()
        }

        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun signUpUser() {
        val email = signupEmail.text.toString()
        val pass = signupPassword.text.toString()
        val confirmPassword = signupPassword2.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {

                Toast.makeText(this, "Singed Up Failed! kindly check details", Toast.LENGTH_SHORT).show()
            }
        }
    }

}