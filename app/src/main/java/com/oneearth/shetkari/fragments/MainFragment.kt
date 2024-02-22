package com.oneearth.shetkari.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.oneearth.shetkari.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Find the view with ID R.id.ShowAll
        val showAllView: View = view.findViewById(R.id.ShowAll)

        // Set a click listener for the ShowAllView
        showAllView.setOnClickListener {
            // Handle the click event to load the CatSeeMore fragment
            loadFragment(CatmoreFragment())
        }

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Replace the existing fragment with the new one
        transaction.replace(R.id.frame_layout, fragment)

        // Add the transaction to the back stack (optional)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }
}


