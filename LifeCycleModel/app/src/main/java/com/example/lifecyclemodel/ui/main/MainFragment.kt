package com.example.lifecyclemodel.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.lifecyclemodel.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        lifecycleScope.launch(Dispatchers.IO) {
            Log.i("MyTag", "Thread is ${Thread.currentThread().name}")
        }

        lifecycleScope.launchWhenCreated {
            //Activity, Fragment 생성 시
        }

        lifecycleScope.launchWhenStarted {
            //Activity, Fragment 시작 시
        }

        lifecycleScope.launchWhenResumed {
            //Activity, Fragment Resume 시
        }
    }

}