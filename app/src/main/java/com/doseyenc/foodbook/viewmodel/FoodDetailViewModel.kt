package com.doseyenc.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doseyenc.foodbook.model.FoodModel
import com.doseyenc.foodbook.service.FoodDAO
import com.doseyenc.foodbook.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {
    val food = MutableLiveData<FoodModel>()
    fun getRoomData(id:Int){
        launch {
            val dao = FoodDatabase(getApplication()).getFoodDao()
            val currentFood = dao.getFoodById(id)
            food.value = currentFood
        }
    }
}