package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {
    lateinit var fragment1_1 : Fragment1_1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: ViewGroup = inflater.inflate(R.layout.fragment1, container, false) as ViewGroup

        fragment1_1 = Fragment1_1()

        childFragmentManager.beginTransaction().replace(R.id.tab1_container, fragment1_1).commit()

        val btn: Button = rootView.findViewById(R.id.btn1_1)
        btn.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.tab1_container, fragment1_1).commit()
        }

        return rootView
    }
}