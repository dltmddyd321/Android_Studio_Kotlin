package com.example.grocercyservice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grocercyservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GroceryListAdapter.GroceryItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var groceryItemsList: List<GroceryItems>
    private lateinit var groceryListAdapter: GroceryListAdapter
    private lateinit var groceryViewModel: GroceryViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        groceryItemsList = ArrayList<GroceryItems>()
        groceryListAdapter = GroceryListAdapter(groceryItemsList, this)
        binding.idRvItems.layoutManager = LinearLayoutManager(this)
        binding.idRvItems.adapter = groceryListAdapter

        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        groceryViewModel.getAllGroceryItems().observe(this) {
            groceryListAdapter.list = it
            groceryListAdapter.notifyDataSetChanged()
        }

        binding.idFabAdd.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        //TODO: Dialog Implement
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryListAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted!", Toast.LENGTH_SHORT).show()
    }
}