package com.kft.foodorder

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kft.foodorder.domain.FoodDBRepository
import com.kft.foodorder.local.FoodDB
import com.kft.foodorder.local.FoodDBDao
import com.kft.foodorder.local.FoodDBRepoImpl
import com.kft.foodorder.local.FoodDatabase
import kotlinx.coroutines.runBlocking
import java.io.InputStreamReader

class MainApplication : Application() {

    lateinit var foodDB : FoodDBRepository
    override fun onCreate() {
        super.onCreate()
        foodDB = FoodDBRepoImpl(this@MainApplication)

        runBlocking {

            Log.d("FoodOrder", "level 1")
            val foodAvailableInDb = foodDB.getAllFoodsOneTime()
            if(foodAvailableInDb.isNotEmpty()){
                Log.d("FoodOrder", "exit")

                return@runBlocking
            }
            val gson = Gson()
            val inputStream = assets.open("FoodList.json")
            val reader = InputStreamReader(inputStream)
            val foodListType = object : TypeToken<List<FoodDB>>() {}.type
            val foodList: List<FoodDB> = gson.fromJson(reader, foodListType)

            foodDB.insertAllToDB(foodList)
            Log.d("FoodOrder", "added")

        }
    }
}