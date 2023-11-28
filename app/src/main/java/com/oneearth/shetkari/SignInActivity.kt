package com.oneearth.shetkari

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
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

    private val RC_SIGN_IN = 9001 // Request code for Google Sign-In

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val signInButton = findViewById<Button>(R.id.signInButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val googleSignInButton = findViewById<Button>(R.id.googlesigninbutton)

        // Initialize Activity Result Launcher
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            handleGoogleSignInResult(data)
        }

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign-in success
                            Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, FarmerProfileActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Sign-in failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            // Navigate to the registration activity
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }

        googleSignInButton.setOnClickListener {
            // Configure Google Sign-In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.your_web_client_id)) // Replace with your web client ID
                .requestEmail()
                .build()

            // Initialize GoogleSignInClient
            val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

            // Start the Google Sign-In intent using the Activity Result API
            val signInIntent = mGoogleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }
    }

    private fun handleGoogleSignInResult(data: Intent?) {
        if (data == null) {
            // Handle the case where data is null (e.g., the user canceled the sign-in)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            return
        }

        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Sign in was successful, you can now use the user's account.
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken

            if (idToken != null) {
                // Got an ID token from Google. Use it to authenticate with Firebase.
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser

                            // Start the Farmer Profile Activity
                            val intent = Intent(this, FarmerProfileActivity::class.java)
                            startActivity(intent)

                            // You can navigate to another activity here if needed
                            Toast.makeText(this, "Sign-in with Google successful", Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Sign-in with Google failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Shouldn't happen.
                Toast.makeText(this, "No ID token!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            // Sign in failed, handle the error
            Toast.makeText(this, "Sign-in with Google failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}
