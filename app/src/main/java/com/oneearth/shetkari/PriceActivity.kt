package com.oneearth.shetkari

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PriceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price)

        if (savedInstanceState == null) {
            // If the activity is created for the first time, add the ApmcFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerView, ApmcFragment())
                .commit()
        }
    }


}
