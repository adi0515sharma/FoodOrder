package com.kft.foodorder.local

import android.content.Context
import com.kft.foodorder.domain.FoodDBRepository
import kotlinx.coroutines.flow.Flow

class FoodDBRepoImpl(context: Context) : FoodDBRepository {



    val foodDBDao : FoodDBDao = FoodDatabase.getInstance(context)?.foodDBDao()!!

    override suspend fun insertAllToDB(list: List<FoodDB>) {
        foodDBDao.insertAllFoods(list)
    }

    override suspend fun changeFavorite(food: FoodDB) {
        foodDBDao.updateFood(food)
    }

    override suspend fun changeCart(food: FoodDB) {
        foodDBDao.updateFood(food)
    }


    override suspend fun deleteAllFoods() {
        foodDBDao.deleteAllFoods()
    }

    override suspend fun getAllFoodsOneTime(): List<FoodDB>  {
        return  foodDBDao.getAllFoodOneTime()
    }


    override fun getAllFavoriteFood(): Flow<List<FoodDB>>  =  foodDBDao.getAllFavoriteFood()

    override fun getAllCardFoods(): Flow<List<FoodDB>> =  foodDBDao.getAllCardFoods()

    override fun getAllFoods(): Flow<List<FoodDB>> =   foodDBDao.getAllFoods()
    override fun getSpecificFood(id : Int): Flow<FoodDB> = foodDBDao.getSpecificFood(id)
}