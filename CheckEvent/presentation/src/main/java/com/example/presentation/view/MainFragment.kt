package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.view.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun init() {
        binding.fragment = this
    }

    fun startBtn(view: View) {
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }
}