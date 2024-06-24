package com.kft.foodorder.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDBDao {

    @Update
    suspend fun updateFood(food: FoodDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFoods(foodList: List<FoodDB>)

    @Query("DELETE FROM FoodDB")
    suspend fun deleteAllFoods()

    @Query("SELECT * FROM FoodDB")
    suspend fun getAllFoodOneTime() : List<FoodDB>

    @Query("SELECT * FROM FoodDB WHERE isFavorite == 1")
    fun getAllFavoriteFood() : Flow<List<FoodDB>>

    @Query("SELECT * FROM FoodDB WHERE cart > 0")
    fun getAllCardFoods() : Flow<List<FoodDB>>

    @Query("SELECT * FROM FoodDB")
    fun getAllFoods() : Flow<List<FoodDB>>

    @Query("SELECT * FROM FoodDB WHERE meal_id = :id")
    fun getSpecificFood(id : Int) : Flow<FoodDB>
}