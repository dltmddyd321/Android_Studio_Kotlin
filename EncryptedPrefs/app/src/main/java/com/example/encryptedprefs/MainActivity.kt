package com.example.encryptedprefs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.encryptedprefs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        val sharedPrefs = EncryptedSharedPreferences.create(
            "encryptedShared",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        binding.run {
            saveButton.setOnClickListener {
                val key = keyEdittext.text.toString()
                val value = valueEdittext.text.toString()
                sharedPrefs.edit().putString(key, value).apply()
            }

            getButton.setOnClickListener {
                val storedValue = sharedPrefs.getString(keyEdittext.text.toString(), "")
                showResultTextview.text = storedValue
            }
        }
    }
}