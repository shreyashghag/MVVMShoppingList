package com.example.mvvmexample.data.repositories

import com.example.mvvmexample.data.db.ShoppingDatabase
import com.example.mvvmexample.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db:ShoppingDatabase
) {
    suspend fun upsert(item:ShoppingItem)=db.getShoppingDoa().upsertItem(item)

    suspend fun delete(item: ShoppingItem)=db.getShoppingDoa().deleteItem(item)

    fun getAllshoppingItem()=db.getShoppingDoa().getAllShoppingItem()
}