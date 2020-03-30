package com.example.mvvmexample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmexample.data.db.entities.ShoppingItem

@Database(entities = [ShoppingItem::class],version = 1)
abstract class ShoppingDatabase :RoomDatabase(){
    abstract fun getShoppingDoa(): ShoppingDao

    companion object{
        @Volatile
        private var instance: ShoppingDatabase?=null

        private var Lock=Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(Lock){
            instance
                ?: createDatabase(
                    context
                ).also { instance =it }

        }

        private fun createDatabase(context:Context)=Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java,"ShoppingList").build()
    }

}