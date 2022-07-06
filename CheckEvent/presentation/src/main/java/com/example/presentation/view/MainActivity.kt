package com.example.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.view.base.BaseActivity
import com.example.presentation.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {

    }
}