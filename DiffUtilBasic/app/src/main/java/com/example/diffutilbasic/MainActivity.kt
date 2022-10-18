package com.example.diffutilbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilbasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dataSet = arrayListOf<Monster>().apply {
        add(Monster("타일런트", Race.Zombie, 10, listOf(100, 10, 50), false))
        add(Monster("조커", Race.Human, 23, listOf(100, 10, 50), false))
        add(Monster("그렘린", Race.Goblin, 2, listOf(100, 10, 50), true))
        add(Monster("리오레우스", Race.Dragon, 18, listOf(100, 10, 50), false))
        add(Monster("사우론", Race.Human, 100, listOf(100, 10, 50), false))
        add(Monster("리바이어던", Race.Dragon, 30, listOf(100, 10, 50), true))
    }

    private val mainRecyclerViewAdapter: MainRecyclerViewAdapter by lazy {
        MainRecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = mainRecyclerViewAdapter
        }
        mainRecyclerViewAdapter.submitList(dataSet)

        val itemTouchHelperCallback = ItemTouchHelper(MainItemTouchHelperCallback(binding.rvList))
        itemTouchHelperCallback.attachToRecyclerView(binding.rvList)

        binding.btnFab.setOnClickListener {
            mainRecyclerViewAdapter.submitList(dataSet.shuffled())
        }
    }
}