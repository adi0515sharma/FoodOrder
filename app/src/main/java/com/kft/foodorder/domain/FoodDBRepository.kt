package com.kft.foodorder.domain

import com.kft.foodorder.local.FoodDB
import kotlinx.coroutines.flow.Flow

interface FoodDBRepository {

    suspend fun insertAllToDB(list : List<FoodDB>)

    suspend fun changeFavorite(food: FoodDB)

    suspend fun changeCart(food: FoodDB)

    suspend fun deleteAllFoods()

    suspend fun getAllFoodsOneTime() : List<FoodDB>


    fun getAllFavoriteFood() : Flow<List<FoodDB>>

    fun getAllCardFoods() : Flow<List<FoodDB>>

    fun getAllFoods() : Flow<List<FoodDB>>

    fun getSpecificFood(id : Int) : Flow<FoodDB>


}