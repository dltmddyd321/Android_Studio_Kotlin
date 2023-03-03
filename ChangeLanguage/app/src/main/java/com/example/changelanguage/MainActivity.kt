package com.example.changelanguage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.changelanguage.LocaleWrapper.setLocale
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelect: TextView
    private lateinit var tvOutput: TextView
    private lateinit var rbEng: Button
    private lateinit var rbKor: Button

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleWrapper.wrap(newBase))
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Lisbon"))

        tvSelect = findViewById(R.id.selectText)
        tvOutput = findViewById(R.id.testText)
        rbEng = findViewById(R.id.eng)
        rbKor = findViewById(R.id.kor)

        rbEng.setOnClickListener {
            changeLanguage(it)
        }

        rbKor.setOnClickListener {
            changeLanguage(it)
        }
    }

    private fun changeLanguage(view: View) {
        val lang = view.tag as String
        setLocale(lang)
        recreate()
    }

    private fun setAppLocale(view: View) {
        val appLocale: LocaleListCompat =
            LocaleListCompat.forLanguageTags(view.tag as String)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}