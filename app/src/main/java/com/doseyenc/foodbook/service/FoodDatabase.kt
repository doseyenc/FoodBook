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
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context: Context): FoodDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java, 
                    "food_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}