package com.doseyenc.foodbook.service

import com.doseyenc.foodbook.helper.Constants
import com.doseyenc.foodbook.model.FoodModel
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {




    @GET(Constants.END_POINT)
    fun getFoods(): Single<List<FoodModel>>
}