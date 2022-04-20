package com.example.retrofitmvvmbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful){
                Log.d("Response",it.body()?.myUserId.toString())
                Log.d("Response",it.body()?.id.toString())
                Log.d("Response",it.body()?.title ?: "None")
                Log.d("Response",it.body()?.body ?: "Body")
                textView.text = it.body()?.title
            }
            else{
                Log.d("Response",it.errorBody().toString())
                textView.text = it.errorBody().toString()
            }
        })
    }
}