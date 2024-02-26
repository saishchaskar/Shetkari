package com.oneearth.shetkari


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.oneearth.shetkari.fragments.CropsFragment
import com.oneearth.shetkari.fragments.MainFragment
import com.oneearth.shetkari.fragments.ProfileFragment
import com.oneearth.shetkari.fragments.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navbar)

        // Load the initial fragment (fragment_main) into the frame layout
        loadFragment(MainFragment())


        val homeImageView: ImageView = findViewById(R.id.Home)
        val cropImageView: ImageView = findViewById(R.id.Crop)
        val priceImageView: ImageView = findViewById(R.id.Price)
        val weatherImageView: ImageView = findViewById(R.id.Weather)
        val profileImageView: ImageView = findViewById(R.id.Profile)


        homeImageView.setOnClickListener {
            loadFragment(MainFragment())
        }
        cropImageView.setOnClickListener {
            loadFragment(CropsFragment())
        }

        priceImageView.setOnClickListener {
            loadFragment(ApmcFragment())
        }

        weatherImageView.setOnClickListener {
            loadFragment(WeatherFragment())
        }

        profileImageView.setOnClickListener {
            loadFragment(ProfileFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Replace the existing fragment with the new one
        transaction.replace(R.id.frame_layout, fragment)

        // Commit the transaction
        transaction.commit()
    }
}



