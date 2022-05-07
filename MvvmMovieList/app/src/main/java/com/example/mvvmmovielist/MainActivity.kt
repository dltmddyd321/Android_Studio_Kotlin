package com.example.mvvmmovielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmmovielist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        //LiveData 객체를 구독하여 변경사항을 감지
        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "$it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(this, Observer{

        })

        viewModel.getAllMovies()
    }
}