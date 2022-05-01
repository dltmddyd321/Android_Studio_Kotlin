package com.example.retrofitmvvmbasic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitmvvmbasic.databinding.ActivityMainBinding
import com.example.retrofitmvvmbasic.paging.PagingAdapter
import com.example.retrofitmvvmbasic.paging.PagingRepository
import com.example.retrofitmvvmbasic.paging.PagingViewModel
import com.example.retrofitmvvmbasic.paging.PagingViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : PagingViewModel
    private lateinit var binding : ActivityMainBinding
    private val myAdapter by lazy { PagingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = PagingRepository()
        val viewModelFactory = PagingViewModelFactory(repository)

        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        viewModel = ViewModelProvider(this, viewModelFactory)[PagingViewModel::class.java]
//        viewModel.myCustomPosts.observe(this, Observer {
//            if(it.isSuccessful) {
//                it.body()?.let { task -> myAdapter.setData(task) }
//            } else {
//                binding.textView.text = it.code().toString()
//            }
//        })

        binding.btn.setOnClickListener {
//            val myNumber = binding.editText.text.toString()
//            viewModel.getPostTwo(myNumber.toInt())
//            viewModel.getCustomPosts(binding.editText.text.toString().toInt())
            viewModel.searchPost(Integer.parseInt(binding.editText.text.toString()))

            //포커스 제거
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.editText.windowToken, 0)
        }

        //옵저빙을 통해 데이터 전달
        viewModel.result.observe(this, Observer {
            myAdapter.submitData(this.lifecycle, it)
        })

//        viewModel.getPost()
//        viewModel.myResponse.observe(this, Observer {
//            if(it.isSuccessful){
//                Log.d("Response",it.body()?.myUserId.toString())
//                Log.d("Response",it.body()?.id.toString())
//                Log.d("Response",it.body()?.title ?: "None")
//                Log.d("Response",it.body()?.body ?: "Body")
//                binding.textView.text = it.body()?.title
//            }
//            else{
//                Log.d("Response",it.errorBody().toString())
//                binding.textView.text = it.errorBody().toString()
//            }
//        })
    }
}