package com.doseyenc.foodbook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodModel(
    val image: String,
    val foodName: String?,
    val foodCalory: String?,
    val foodCarbonHydrat: String?,
    val foodProtein: String?,
    val foodFat: String?
):Parcelable