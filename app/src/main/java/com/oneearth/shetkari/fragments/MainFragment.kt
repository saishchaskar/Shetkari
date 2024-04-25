package com.oneearth.shetkari.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oneearth.shetkari.Adapter.NewsAdapter
import com.oneearth.shetkari.R
import com.oneearth.shetkari.utilities.NewsApiClient

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter // Assuming you have a NewsAdapter class

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Find the view with ID R.id.ShowAll
        val showAllView: View = view.findViewById(R.id.ShowAll)
        val seemoreView: View = view.findViewById(R.id.See)

        // Set a click listener for the ShowAllView
        showAllView.setOnClickListener {
            // Handle the click event to load the CatSeeMore fragment
            loadFragment(CatmoreFragment())
        }
        seemoreView.setOnClickListener{
            loadFragment(ArtmoreFragment())
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.View)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter()
        recyclerView.adapter = adapter

        // Fetch news
        fetchNews()

        return view
    }


    private fun fetchNews() {
        NewsApiClient.fetchNews { articles, error ->
            activity?.runOnUiThread {
                if (error != null) {
                    Log.e("NewsApiClient", "Error fetching news: $error")
                    // Handle error
                } else {
                    if (articles != null) {
                        // Update adapter with new data
                        adapter.submitList(articles)
                    } else {
                        Log.e("NewsApiClient", "No news articles received")
                        // Handle no articles
                    }
                }
            }
        }
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
