package com.doseyenc.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doseyenc.foodbook.model.FoodModel

class FoodListViewModel : ViewModel() {
    fun refresh_data() {
        TODO("Not yet implemented")
    }

    val foods = MutableLiveData<List<FoodModel>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()


}