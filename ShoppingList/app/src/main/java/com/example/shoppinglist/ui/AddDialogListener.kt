package com.example.shoppinglist.ui

import com.example.shoppinglist.data.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}