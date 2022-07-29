package com.doseyenc.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doseyenc.foodbook.model.FoodModel

class FoodDetailViewModel : ViewModel() {
    val food = MutableLiveData<FoodModel>()
}