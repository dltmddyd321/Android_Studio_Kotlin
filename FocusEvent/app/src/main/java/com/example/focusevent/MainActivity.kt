package com.example.focusevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var ePrice1 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ePrice1 = findViewById(R.id.editPrice1)

        ePrice1.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if(focus) {
                Toast.makeText(this, "포커스!!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "OUT", Toast.LENGTH_SHORT).show()
            }
        }

    }
}