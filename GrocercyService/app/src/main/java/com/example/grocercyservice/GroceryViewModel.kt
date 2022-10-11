package com.example.grocercyservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository): ViewModel() {
    fun insert(items: GroceryItems) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(items)
    }

    fun delete(items: GroceryItems) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(items)
    }

    fun getAllGroceryItems() = repository.getAllItems()
}