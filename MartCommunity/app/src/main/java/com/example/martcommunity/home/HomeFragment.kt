package com.example.martcommunity.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.martcommunity.R
import com.example.martcommunity.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding
    }

}