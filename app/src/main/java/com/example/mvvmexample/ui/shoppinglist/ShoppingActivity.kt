package com.example.mvvmexample.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmexample.R
import com.example.mvvmexample.adapter.ShoppingItemAdapter
import com.example.mvvmexample.data.db.ShoppingDatabase
import com.example.mvvmexample.data.db.entities.ShoppingItem
import com.example.mvvmexample.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()
    private val factory:ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
/*
        val database=ShoppingDatabase(this)
        val repository=ShoppingRepository(database)
        val factory=ShoppingViewModelFactory(repository)
*/
        val viewModel=ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
        var adapter=ShoppingItemAdapter(listOf(),viewModel)
        rvShoppingItems.layoutManager=LinearLayoutManager(this)
        rvShoppingItems.adapter=adapter

        viewModel.getAllShoppingItem().observe(this, Observer {
            adapter.items=it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            ShoppingItemDialog(this,
                object :AddDialogListener{
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }

    }

}
