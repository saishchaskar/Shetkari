package com.oneearth.shetkari

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var signupRedirectText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUsername = findViewById(R.id.login_username)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)
        signupRedirectText = findViewById(R.id.signupRedirectText)

        loginButton.setOnClickListener {
            Log.d("LoginActivity", "Login button clicked")
            if (validateUsername() && validatePassword()) {
                Log.d("LoginActivity", "Username and password validated")
                checkUser()
            } else {
                Log.d("LoginActivity", "Validation failed")
            }
        }


        signupRedirectText.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateUsername(): Boolean {
        val username = loginUsername.text.toString().trim()
        return if (username.isEmpty()) {
            loginUsername.error = "Username cannot be empty"
            false
        } else {
            loginUsername.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = loginPassword.text.toString().trim()
        return if (password.isEmpty()) {
            loginPassword.error = "Password cannot be empty"
            false
        } else {
            loginPassword.error = null
            true
        }
    }

    private fun checkUser() {
        Log.d("LoginActivity", "Checking user")
        val userUsername = loginUsername.text.toString().trim()
        val userPassword = loginPassword.text.toString().trim()
        val reference = FirebaseDatabase.getInstance().getReference("users")
        val query = reference.orderByChild("username").equalTo(userUsername)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val passwordFromDB =
                            userSnapshot.child("password").getValue(String::class.java)
                        if (passwordFromDB == userPassword) {
                            val nameFromDB = userSnapshot.child("name").getValue(String::class.java)
                            val emailFromDB =
                                userSnapshot.child("email").getValue(String::class.java)
                            val usernameFromDB =
                                userSnapshot.child("username").getValue(String::class.java)

                            // Perform login, e.g., store user data in SharedPreferences or start MainActivity
                            performLogin(nameFromDB, emailFromDB, usernameFromDB)
                            return // Exit the function after successful login
                        } else {
                            loginPassword.error = "Invalid Credentials"
                            loginPassword.requestFocus()
                        }
                    }
                } else {
                    loginUsername.error = "User does not exist"
                    loginUsername.requestFocus()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("LoginActivity", "Database error: ${error.message}")
            }
        })
    }

    private fun performLogin(name: String?, email: String?, username: String?) {
        // Perform actions to log in the user
        // For example, store user data locally, start MainActivity, etc.
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish LoginActivity to prevent user from going back
    }

}