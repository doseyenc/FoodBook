package com.doseyenc.foodbook.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.doseyenc.foodbook.model.FoodModel

@Database(entities = arrayOf(FoodModel::class), version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDAO


    companion object {
        @Volatile
        private var instance: FoodDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: getDatabase(context).also {
                instance = it
            }
        }

        private fun getDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodDatabase::class.java, "foodDatabase"
        ).build()
    }
}