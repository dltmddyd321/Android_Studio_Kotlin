package com.sycompany.viewmodelfactorysecond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sycompany.viewmodelfactorysecond.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainViewModelFactory("Counter Result", 999)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.apply {
            textTitle.text = viewModel.getTitle()
            textResult.text = viewModel.getResult().toString()

            buttonPlus.setOnClickListener {
                viewModel.setPlus(editInput.text.toString().toInt())
                textResult.text = viewModel.getResult().toString()
            }

            buttonMinus.setOnClickListener {
                viewModel.setMinus(editInput.text.toString().toInt())
                textResult.text = viewModel.getResult().toString()
            }

        }
    }
}