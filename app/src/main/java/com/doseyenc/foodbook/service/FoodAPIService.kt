package com.doseyenc.foodbook.service

import com.doseyenc.foodbook.helper.Constants
import com.doseyenc.foodbook.model.FoodModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FoodAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    fun getData():Single<List<FoodModel>>{
        return api.getFoods()
    }
}