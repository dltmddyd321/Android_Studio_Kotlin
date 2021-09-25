package com.example.martcommunity.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martcommunity.R
import com.example.martcommunity.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var articleAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        articleAdapter = ArticleAdapter()

        //RecyclerView를 지정하여 프래그먼트와 바인딩 연결
        fragmentHomeBinding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        //값을 배정하기 위한 Adapter 연결
        fragmentHomeBinding.articleRecyclerView.adapter = articleAdapter
    }

}