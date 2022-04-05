package com.example.contacts

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class AddActivity : AppCompatActivity() {

    lateinit var add_edittext_name : EditText
    lateinit var add_edittext_number : EditText
    lateinit var add_button : Button
    private lateinit var contactViewModel : ContactViewModel
    private var id: Long?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        add_edittext_name = findViewById(R.id.add_edittext_name)
        add_edittext_number = findViewById(R.id.add_edittext_number)
        add_button = findViewById(R.id.add_button)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        if(intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER) && intent.hasExtra(EXTRA_CONTACT_ID)) {
            add_edittext_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString().trim()
            val number = add_edittext_number.text.toString()

            if(name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "Please enter name and number.", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].uppercase() as Char
                val contact = Contact(id, name, number, initial)
                contactViewModel.insert(contact)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}