package com.oneearth.shetkari.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.oneearth.shetkari.R


class ProfileFragment : Fragment() {
    private var profileName: TextView? = null
    private var profileEmail: TextView? = null
    private var profileUsername: TextView? = null
    private var profilePassword: TextView? = null
    private var titleName: TextView? = null
    private var titleUsername: TextView? = null
    private var editProfile: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_user_profile, container, false)
        profileName = view.findViewById<TextView>(R.id.profileName)
        profileEmail = view.findViewById<TextView>(R.id.profileEmail)
        profileUsername = view.findViewById<TextView>(R.id.profileUsername)
        profilePassword = view.findViewById<TextView>(R.id.profilePassword)
        titleName = view.findViewById<TextView>(R.id.titleName)
        titleUsername = view.findViewById<TextView>(R.id.titleUsername)
        editProfile = view.findViewById<Button>(R.id.editButton)

        // Ensure editProfile is not null before using it
        editProfile?.setOnClickListener(View.OnClickListener { passUserData() })

        showUserData()
        return view
    }


    fun showUserData() {
        val args = arguments
        if (args != null) {
            val nameUser = args.getString("name")
            val emailUser = args.getString("email")
            val usernameUser = args.getString("username")
            val passwordUser = args.getString("password")
            titleName!!.text = nameUser
            titleUsername!!.text = usernameUser
            profileName!!.text = nameUser
            profileEmail!!.text = emailUser
            profileUsername!!.text = usernameUser
            profilePassword!!.text = passwordUser
        }
    }

    fun passUserData() {
        val userUsername = profileUsername!!.text.toString().trim { it <= ' ' }
        val reference = FirebaseDatabase.getInstance().getReference("users")
        val checkUserDatabase = reference.orderByChild("username").equalTo(userUsername)
        checkUserDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val nameFromDB = snapshot.child(userUsername).child("name").getValue(
                        String::class.java
                    )
                    val emailFromDB = snapshot.child(userUsername).child("email").getValue(
                        String::class.java
                    )
                    val usernameFromDB = snapshot.child(userUsername).child("username").getValue(
                        String::class.java
                    )
                    val passwordFromDB = snapshot.child(userUsername).child("password").getValue(
                        String::class.java
                    )
                    val args = Bundle()
                    args.putString("name", nameFromDB)
                    args.putString("email", emailFromDB)
                    args.putString("username", usernameFromDB)
                    args.putString("password", passwordFromDB)
                    val fragment = EditProfileFragment()
                    fragment.setArguments(args)
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}

