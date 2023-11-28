package com.oneearth.shetkari

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LanguageSelectionActivity : AppCompatActivity() {
    private lateinit var buttonEnglish: Button
    private lateinit var buttonMarathi: Button
    private lateinit var buttonHindi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)

        buttonEnglish = findViewById(R.id.button_english)
        buttonMarathi = findViewById(R.id.buttonMarathi)
        buttonHindi = findViewById(R.id.buttonHindi)

        val selectedLanguage = getSavedLanguage()
        if (selectedLanguage.isNotEmpty()) {
            startMainActivity(selectedLanguage)
        }

        buttonEnglish.setOnClickListener {
            startMainActivity("English")
        }

        buttonMarathi.setOnClickListener {
            startMainActivity("Marathi")
        }

        buttonHindi.setOnClickListener {
            startMainActivity("Hindi")
        }
    }

    private fun getSavedLanguage(): String {
        // Retrieve the saved language preference (e.g., from SharedPreferences)
        val sharedPref = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        return sharedPref.getString("selectedLanguage", "") ?: ""
    }

    private fun saveLanguagePreference(language: String) {
        // Save the selected language preference (e.g., in SharedPreferences)
        val sharedPref = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("selectedLanguage", language)
        editor.apply()
    }

    // Inside LanguageSelectionActivity
    private fun startMainActivity(selectedLanguage: String) {
        // Save the selected language preference
        saveLanguagePreference(selectedLanguage)

        // Set a flag to indicate that language selection has been done
        setLanguageSelectedFlag(true)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("selectedLanguage", selectedLanguage)
        startActivity(intent)
        finish()
    }

    private fun setLanguageSelectedFlag(selected: Boolean) {
        val sharedPref = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("languageSelected", selected)
        editor.apply()
    }
}
