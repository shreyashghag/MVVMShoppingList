package com.example.mvvmexample.ui.shoppinglist

import com.example.mvvmexample.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}