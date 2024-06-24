package com.kft.foodorder.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "FoodDB")
data class FoodDB(
    @PrimaryKey val meal_id: Int,
    val img: String,
    val food_name: String,
    val food_category: String,
    val rp_field: String,
    val time: String,
    val distance: String,
    val rating: Double,
    var cart: Int = 0,
    var isFavorite: Boolean = false
): Parcelable


enum class CART_ACTION{
    ADD,
    MINUS,
    REMOVE
}