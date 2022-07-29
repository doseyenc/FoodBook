package com.doseyenc.foodbook.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodModel(
    @SerializedName("gorsel")
    val image: String,
    @SerializedName("isim")
    val foodName: String?,
    @SerializedName("kalori")
    val foodCalory: String?,
    @SerializedName("karbonhidrat")
    val foodCarbonHydrat: String?,
    @SerializedName("protein")
    val foodProtein: String?,
    @SerializedName("yag")
    val foodFat: String?
):Parcelable