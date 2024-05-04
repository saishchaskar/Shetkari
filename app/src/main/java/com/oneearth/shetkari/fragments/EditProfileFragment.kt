package com.oneearth.shetkari.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.oneearth.shetkari.R

class EditProfileFragment : Fragment() {

    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText
    private lateinit var saveButton: Button
    private lateinit var nameUser: String
    private lateinit var emailUser: String
    private lateinit var usernameUser: String
    private lateinit var passwordUser: String
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        // Initialize Firebase reference
        reference = FirebaseDatabase.getInstance().getReference("users")

        // Initialize views
        editName = view.findViewById(R.id.editName)
        editEmail = view.findViewById(R.id.editEmail)
        editUsername = view.findViewById(R.id.editUsername)
        editPassword = view.findViewById(R.id.editPassword)
//        saveButton = view.findViewById(R.id.saveButton)

        // Show user data in EditText fields
        showData()

        // Set onClickListener for save button
//        saveButton.setOnClickListener {
//            // Check if any data is changed and update it in the database
//            if (isNameChanged() || isEmailChanged() || isPasswordChanged()) {
//                Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(requireContext(), "No changes made", Toast.LENGTH_SHORT).show()
//            }
//        }

        return view
    }

    private fun showData() {
        // Retrieve user data from fragment arguments
        arguments?.let { args ->
            nameUser = args.getString("name", "")
            emailUser = args.getString("email", "")
            usernameUser = args.getString("username", "")
            passwordUser = args.getString("password", "")

            // Display user data in EditText fields
            editName.setText(nameUser)
            editEmail.setText(emailUser)
            editUsername.setText(usernameUser)
            editPassword.setText(passwordUser)
        }
    }

    // Function to update user name in the database if changed
    private fun isNameChanged(): Boolean {
        val newName = editName.text.toString()
        if (newName != nameUser) {
            reference.child(usernameUser).child("name").setValue(newName)
            nameUser = newName
            return true
        }
        return false
    }

    // Function to update user email in the database if changed
    private fun isEmailChanged(): Boolean {
        val newEmail = editEmail.text.toString()
        if (newEmail != emailUser) {
            reference.child(usernameUser).child("email").setValue(newEmail)
            emailUser = newEmail
            return true
        }
        return false
    }

    // Function to update user password in the database if changed
    private fun isPasswordChanged(): Boolean {
        val newPassword = editPassword.text.toString()
        if (newPassword != passwordUser) {
            reference.child(usernameUser).child("password").setValue(newPassword)
            passwordUser = newPassword
            return true
        }
        return false
    }
}
