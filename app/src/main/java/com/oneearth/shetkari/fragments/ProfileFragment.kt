package com.oneearth.shetkari.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.oneearth.shetkari.R


class ProfileFragment : Fragment() {
    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profileUsername: TextView
    private lateinit var titleName: TextView
    private lateinit var titleUsername: TextView
    private lateinit var editProfile: Button
    private lateinit var database: DatabaseReference
    private var currentUser: FirebaseUser? = null
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_user_profile, container, false)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance().reference
        currentUser = FirebaseAuth.getInstance().currentUser

        // Initialize views
        profileName = view.findViewById(R.id.profileName)
        profileEmail = view.findViewById(R.id.profileEmail)
        profileUsername = view.findViewById(R.id.profileUsername)
        titleName = view.findViewById(R.id.titleName)
        titleUsername = view.findViewById(R.id.titleUsername)
        editProfile = view.findViewById(R.id.editButton)

        editProfile.setOnClickListener { passUserData() }

        // Fetch and display user data
        showUserData()

        return view
    }

    private fun showUserData() {
        // Fetch user data from Firebase based on the current user's username
        currentUser?.let { user ->
            username = user.displayName
            username?.let { uname ->
                database.child("users").child(uname).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val name = snapshot.child("name").getValue(String::class.java)
                            val email = snapshot.child("email").getValue(String::class.java)
                            val username = snapshot.child("username").getValue(String::class.java)

                            // Update UI with fetched data
                            titleName.text = name
                            titleUsername.text = username
                            profileName.text = name
                            profileEmail.text = email
                            profileUsername.text = username
                        } else {
                            Log.d("ProfileFragment", "No data found for username: $uname")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("ProfileFragment", "Database error: ${error.message}")
                    }
                })
            }
        }
    }

    private fun passUserData() {
        // Pass user data to EditProfileFragment
        val fragment = EditProfileFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
