package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.data.db.ShoppingDatabase

class ShoppingRepository(private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItem() = db.getShoppingDao().getAllShoppingItems()
}