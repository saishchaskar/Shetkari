package com.oneearth.shetkari

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class FarmerProfileActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var profilePictureImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userprofile)

        profilePictureImageView = findViewById(R.id.profile_picture)
        profilePictureImageView.setOnClickListener {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data

            // Set the selected image to the profile picture ImageView
            profilePictureImageView.setImageURI(selectedImageUri)
        }
    }
}
