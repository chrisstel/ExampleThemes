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

        when (getThemeType()) {
            "dark" -> setDarkTheme()
            else -> setLightTheme()
        }

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.btnDarkTheme.setOnClickListener {
            setDarkTheme()
            saveThemeType("dark")
        }


        _binding.btnLightTheme.setOnClickListener {
            setLightTheme()
            saveThemeType("light")
        }
    }

    private fun setLightTheme() = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    private fun setDarkTheme() = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

    private fun saveThemeType(type: String) {
        val sharedPreferences = getSharedPreferences("ThemeAppearance", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply {
            putString("themeType", type)
            editor.apply()
        }
    }
    private fun getThemeType() = getSharedPreferences("ThemeAppearance", Context.MODE_PRIVATE).getString("themeType", "default")
}