package com.example.contacts

import android.arch.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel : ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contact ->
            //Update UI
        })
    }
}