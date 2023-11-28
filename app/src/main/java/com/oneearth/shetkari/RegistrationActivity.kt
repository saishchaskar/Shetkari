package com.oneearth.shetkari

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class RegistrationActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    private val RC_SIGN_IN = 9001

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val registerEmailEditText = findViewById<EditText>(R.id.registerEmailEditText)
        val registerPasswordEditText = findViewById<EditText>(R.id.registerPasswordEditText)
        val backToSignInButton = findViewById<Button>(R.id.backToSignInButton)
        val signinwithgoogle = findViewById<Button>(R.id.signinwithgoogle)

        registerButton.setOnClickListener {
            val email = registerEmailEditText.text.toString().trim()
            val password = registerPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registration success
                            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                            // Navigate to the Sign-In activity
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Registration failed. Please try again.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        backToSignInButton.setOnClickListener {
            // Navigate back to the sign-in activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        // Initialize the Google Sign-In launcher
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                onActivityResult(RC_SIGN_IN, result.resultCode, data)
            }
        }

        signinwithgoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.your_web_client_id)) // Replace with your web client ID
                .requestEmail()
                .build()

            // Initialize GoogleSignInClient
            val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

            // Start the Google Sign-In intent using the launcher
            val signInIntent = mGoogleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)


        }
    }


}
