package com.example.examplethemes

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.examplethemes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var _binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCurrentTheme()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.btnLightTheme.setOnClickListener {
            setLightTheme()
            saveThemeType("light")
        }

        _binding.btnGreenTheme.setOnClickListener {
            setGreenTheme()
            saveThemeType("green")
        }

        _binding.btnDarkTheme.setOnClickListener {
            setDarkTheme()
            saveThemeType("dark")
        }
    }

    private fun setCurrentTheme() {
        when (getThemeType()) {
            "dark" -> setDarkTheme()
            "green" -> setGreenTheme()
            else -> setLightTheme()
        }
    }

    private fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        recreate()
    }

    private fun setGreenTheme() {
        setTheme(R.style.Theme_Green_ExampleThemes)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        recreate()
    }

    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        recreate()
    }

    private fun getThemeType() = getSharedPreferences("ThemeAppearance", Context.MODE_PRIVATE).getString("themeType", "default")

    private fun saveThemeType(type: String) {
        val sharedPreferences = getSharedPreferences("ThemeAppearance", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply {
            putString("themeType", type)
            editor.apply()
        }
    }
}