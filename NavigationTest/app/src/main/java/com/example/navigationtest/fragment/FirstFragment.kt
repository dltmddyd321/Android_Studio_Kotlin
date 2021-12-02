package com.example.navigationtest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.navigationtest.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val bundle = bundleOf("message" to "i'm from first fragment!")

        val message = arguments?.getString("message")

        textview2.text = message

        textview.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }
    }
}