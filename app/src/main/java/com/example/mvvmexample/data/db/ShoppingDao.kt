package com.example.mvvmexample.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmexample.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    @Query("select * from shopping_item")
    fun getAllShoppingItem():LiveData<List<ShoppingItem>>
}