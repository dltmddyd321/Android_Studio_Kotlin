package com.example.grocercyservice

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

    @SuppressLint("NotifyDataSetChanged")
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        val addBtn = dialog.findViewById<Button>(R.id.addBtn)
        val itemEditName = dialog.findViewById<EditText>(R.id.editTextName)
        val itemEditQauntity = dialog.findViewById<EditText>(R.id.editTextQauntity)
        val itemEditPrice = dialog.findViewById<EditText>(R.id.editTextPrice)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        addBtn.setOnClickListener {
            val itemName = itemEditName.text.toString()
            val itemPrice = itemEditPrice.text.toString()
            val itemQuantity = itemEditQauntity.text.toString()
            val quantity = itemQuantity.toInt()
            val price = itemPrice.toInt()

            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
                val items = GroceryItems(itemName, quantity, price)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext, "Item inserted!", Toast.LENGTH_SHORT).show()
                groceryListAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(applicationContext, "Please enter all the data..", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryListAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted!", Toast.LENGTH_SHORT).show()
    }
}