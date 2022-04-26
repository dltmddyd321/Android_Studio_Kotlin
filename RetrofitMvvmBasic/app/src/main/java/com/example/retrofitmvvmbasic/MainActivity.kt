package com.example.retrofitmvvmbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvmbasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.myResponseTwo.observe(this, Observer {
            if(it.isSuccessful) {
                binding.textView.text = it.body().toString()
            } else {
                binding.textView.text = it.code().toString()
            }
        })

        binding.btn.setOnClickListener {
            val myNumber = binding.editText.text.toString()
            viewModel.getPostTwo(myNumber.toInt())
        }

        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful){
                Log.d("Response",it.body()?.myUserId.toString())
                Log.d("Response",it.body()?.id.toString())
                Log.d("Response",it.body()?.title ?: "None")
                Log.d("Response",it.body()?.body ?: "Body")
                binding.textView.text = it.body()?.title
            }
            else{
                Log.d("Response",it.errorBody().toString())
                binding.textView.text = it.errorBody().toString()
            }
        })
    }
}