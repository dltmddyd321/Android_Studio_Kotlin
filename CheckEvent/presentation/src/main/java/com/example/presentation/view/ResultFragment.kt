package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentResultBinding
import com.example.presentation.view.base.BaseFragment

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    override fun init() {
        binding.fragment = this
    }

    fun backMainBtn(view: View) {
        this.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
    }
}