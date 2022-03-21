package com.example.roomexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var db : LoginDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LoginDatabase.getInstance(applicationContext)!!

        refreshLogin()

        binding.btn.setOnClickListener {
            addLogin()
            refreshLogin()
            startLogin()
        }
    }

    private fun startLogin() {
        Toast.makeText(this, "Success Login!", Toast.LENGTH_SHORT).show()
    }

    private fun addLogin() {
        val userName = binding.etID.text.toString()
        val password = binding.etPW.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            db.loginDao().insertLogin(LoginEntity(userName, password))
        }
    }

    private fun refreshLogin() {
        CoroutineScope(Dispatchers.Main).launch {
            val data =
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                    db.loginDao().getLogin()
                }
            if(data != null) {
                binding.etID.setText(data.userName)
                binding.etPW.setText(data.password)
            }
        }
    }
}