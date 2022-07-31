package com.doseyenc.foodbook.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.doseyenc.foodbook.model.FoodModel

@Dao
interface FoodDAO {
    @Insert
    suspend fun insertAllFoods(vararg foods: FoodModel): List<Long>

    @Query("SELECT * FROM Foods")
    suspend fun getAllFoods(): List<FoodModel>

    @Query("SELECT * FROM Foods WHERE id = :foodId")
    suspend fun getFoodById(foodId: Int): FoodModel

    @Query("Delete FROM Foods")
    suspend fun deleteAllFoods()
}