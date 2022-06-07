package com.example.datastoremodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.datastoremodel.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.name.observe(this) {
            binding.tvName.text = it.toString()
        }

        viewModel.age.observe(this) {
            binding.tvAge.text = it.toString()
        }

        binding.btnSaveData.setOnClickListener {
            viewModel.setName(binding.etUserName.text.toString())
            viewModel.setAge(binding.etUserAge.text.toString().toInt())
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkData() {
        CoroutineScope(Dispatchers.Main).launch {
            val text = "My Sample!"
            SampleApplication.getInstance().getDataStore().setText(text)
            Log.d("Result", SampleApplication.getInstance().getDataStore().text.first())
        }
    }
}