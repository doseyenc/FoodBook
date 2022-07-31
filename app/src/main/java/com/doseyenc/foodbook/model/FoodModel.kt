package com.doseyenc.foodbook.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Foods")
data class FoodModel(

    @ColumnInfo(name = "Image")
    @SerializedName("gorsel")
    val image: String,

    @ColumnInfo(name = "Name")
    @SerializedName("isim")
    val foodName: String?,

    @ColumnInfo(name = "Calory")
    @SerializedName("kalori")
    val foodCalory: String?,

    @ColumnInfo(name = "Carbonhydrate")
    @SerializedName("karbonhidrat")
    val foodCarbonHydrat: String?,

    @ColumnInfo(name = "Protein")
    @SerializedName("protein")
    val foodProtein: String?,

    @ColumnInfo(name = "fat")
    @SerializedName("yag")
    val foodFat: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}